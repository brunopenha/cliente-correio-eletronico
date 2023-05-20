package br.nom.penha.bruno.controladores.servicos;

import br.nom.penha.bruno.controladores.AcessoCorreioResultado;
import br.nom.penha.bruno.dto.ContaCorreio;
import br.nom.penha.bruno.gerenciadores.CorreioGerenciador;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;

public class AcessoServico extends Service<AcessoCorreioResultado> {

    ContaCorreio conta;
    CorreioGerenciador gerenciador;

    public AcessoServico(ContaCorreio conta, CorreioGerenciador gerenciador) {

        this.conta = conta;
        this.gerenciador = gerenciador;
    }

    private AcessoCorreioResultado acesso(){

        Authenticator autenticador = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(conta.getEndereco(), conta.getSenha());
            }
        };

        try {
            Session sessao = Session.getInstance(conta.getPropriedades(),autenticador);
            Store caixa = sessao.getStore("imaps");
            caixa.connect(conta.getPropriedades().getProperty("entrada"),conta.getEndereco(), conta.getSenha());
            conta.setCaixa(caixa);

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            return AcessoCorreioResultado.FALHOU_POR_ERRO_REDE;
        } catch (AuthenticationFailedException e){
            e.printStackTrace();
            return AcessoCorreioResultado.FALHOU_POR_AUTENTICACAO;
        } catch (MessagingException e) {
            e.printStackTrace();
            return AcessoCorreioResultado.FALHOU_POR_ERRO_INESPERADO;
        } catch (Exception e ){
            e.printStackTrace();
            return AcessoCorreioResultado.FALHOU_POR_ERRO_INESPERADO;
        }

        return AcessoCorreioResultado.SUCESSO;

    }

    @Override
    protected Task createTask() {
        return new Task() {
            @Override
            protected AcessoCorreioResultado call() throws Exception {
                return acesso();
            }
        };
    }
}
