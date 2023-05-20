package br.nom.penha.bruno.controladores;

import br.nom.penha.bruno.controladores.servicos.AcessoServico;
import br.nom.penha.bruno.dto.ContaCorreio;
import br.nom.penha.bruno.gerenciadores.CorreioGerenciador;
import br.nom.penha.bruno.visao.VisaoFactory;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaAcessoController extends BaseController {

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
    }

    @FXML
    void acaoBotaoAcesso() {

        System.out.println("Login clicado");
        if(camposSaoValidos()){
            ContaCorreio conta = new ContaCorreio(campoEndCorreio.getText(),campoSenha.getText(),debugCB.isSelected());
            AcessoServico acessoServico = new AcessoServico(conta,correio);

            acessoServico.start();
            acessoServico.setOnSucceeded(evento -> {

                AcessoCorreioResultado resultado = acessoServico.getValue();
                switch (resultado){
                    case SUCESSO -> {
                        System.out.println("Acesso com sucesso " + conta);
                        visao.exibeTelaCorreio();
                        visao.fechaTela((Stage) labelError.getScene().getWindow());
                    }
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

}

