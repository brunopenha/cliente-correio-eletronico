package br.nom.penha.bruno.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class TelaAcessoController {

    @FXML
    private Label labelError;

    @FXML
    private TextField campoEndCorreio;

    @FXML
    private PasswordField campoSenha;

    @FXML
    void acaoBotaoAcesso() {

        System.out.println("Login clicado");
    }

}

