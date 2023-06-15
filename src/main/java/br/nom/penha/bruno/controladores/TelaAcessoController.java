package br.nom.penha.bruno.controladores;

import br.nom.penha.bruno.controladores.servicos.AcessoServico;
import br.nom.penha.bruno.dto.ContaCorreio;
import br.nom.penha.bruno.gerenciadores.CorreioGerenciador;
import br.nom.penha.bruno.visao.VisaoFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class TelaAcessoController extends BaseController implements Initializable {

    AcessoServico acessoServico;
    @FXML
    private Label labelError;

    @FXML
    private TextField campoEndCorreio;

    @FXML
    private PasswordField campoSenha;

    @FXML
    private CheckBox debugCB;

    public TelaAcessoController(CorreioGerenciador correio, VisaoFactory visao, String nomeArquivoFxml) {
        super(correio, visao, nomeArquivoFxml);
            acessoServico = new AcessoServico(correio);

    }

    public TelaAcessoController(CorreioGerenciador correio, VisaoFactory visao, TextField campoUsuario, PasswordField campoSenha, Label erroLabel,CheckBox opcaoDebug,AcessoServico servico, String paginaAcesso) {
        super(correio, visao, paginaAcesso);
        this.campoEndCorreio = campoUsuario;
        this.campoSenha = campoSenha;
        this.labelError = erroLabel;
        this.debugCB = opcaoDebug;
        this.acessoServico = servico;
    }

    @FXML
    void acaoBotaoAcesso() {

        System.out.println("Login clicado");
        if(camposSaoValidos()){
            ContaCorreio conta = new ContaCorreio(campoEndCorreio.getText(),campoSenha.getText(),debugCB.isSelected());
            acessoServico.setConta(conta);
            acessoServico.start();
            acessoServico.setOnSucceeded(evento -> {

                AcessoCorreioResultado resultado = acessoServico.getValue();
                switch (resultado){
                    case SUCESSO -> {
                        System.out.println("Acesso com sucesso " + conta);
                        if(!visao.isTelaPrincipalInicializada()){
                            visao.exibeTelaCorreio();
                        }
                        visao.fechaTela((Stage) labelError.getScene().getWindow());
                    }
                    case FALHOU_POR_AUTENTICACAO -> labelError.setText("Credenciais inválidas!");
                    case FALHOU_POR_ERRO_REDE -> labelError.setText("Problema na rede!");
                    default -> labelError.setText("Erro inesperado!");
                }

            });
        }
    }

    private boolean camposSaoValidos() {
        if(campoEndCorreio.getText().isEmpty()){
            labelError.setText("Informe o email");
            return false;
        }
        if(campoSenha.getText().isEmpty()){
            labelError.setText("Informe a senha");
            return false;
        }

        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Properties propriedades = new Properties();

        try {
            propriedades.load(getClass().getResourceAsStream("/configuracoes.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(null != propriedades.getProperty("usuario") && !propriedades.getProperty("usuario").trim().isEmpty()){
            campoEndCorreio.setText(propriedades.getProperty("usuario"));
        }else{
            campoEndCorreio.setText("");
        }
        if(null != propriedades.getProperty("senha") && !propriedades.getProperty("senha").trim().isEmpty()){
            campoSenha.setText(propriedades.getProperty("senha"));
        }else{
            campoSenha.setText("");
        }
    }
}

