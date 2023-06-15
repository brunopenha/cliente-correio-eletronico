package br.nom.penha.bruno.controladores.servicos;

import br.nom.penha.bruno.controladores.AcessoCorreioResultado;
import br.nom.penha.bruno.dto.ContaCorreio;
import br.nom.penha.bruno.gerenciadores.CorreioGerenciador;
import com.sun.mail.util.MailConnectException;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;

public class AcessoServico extends Service<AcessoCorreioResultado> {

    ContaCorreio conta;
    CorreioGerenciador gerenciador;

    public AcessoServico(CorreioGerenciador gerenciador) {

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
            conta.setSessao(sessao);
            Store caixa = sessao.getStore("imaps");
            caixa.connect(conta.getPropriedades().getProperty("entrada"),conta.getEndereco(), conta.getSenha());
            conta.setCaixa(caixa);
            gerenciador.adicionaContaCorreio(conta);

        } catch (NoSuchProviderException | MailConnectException e) {
            e.printStackTrace();
            return AcessoCorreioResultado.FALHOU_POR_ERRO_REDE;
        } catch (AuthenticationFailedException e){
            e.printStackTrace();
            return AcessoCorreioResultado.FALHOU_POR_AUTENTICACAO;
        } catch (Exception e ){
            e.printStackTrace();
            return AcessoCorreioResultado.FALHOU_POR_ERRO_INESPERADO;
        }

        return AcessoCorreioResultado.SUCESSO;

    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    protected Task createTask() {
        return new Task() {
            @Override
            protected AcessoCorreioResultado call() {
                return acesso();
            }
        };
    }

    public void setConta(ContaCorreio conta) {
        this.conta = conta;
    }
}
