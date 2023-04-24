package br.nom.penha.bruno.controladores;

import br.nom.penha.bruno.gerenciadores.CorreioManager;
import br.nom.penha.bruno.visao.VisaoFactory;
import javafx.fxml.FXML;
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

    public TelaAcessoController(CorreioManager correio, VisaoFactory visao, String nomeArquivoFxml) {
        super(correio, visao, nomeArquivoFxml);
    }

    @FXML
    void acaoBotaoAcesso() {

        System.out.println("Login clicado");

        visao.exibeTelaCorreio();
        visao.encerraPalco((Stage) labelError.getScene().getWindow());
    }

}

