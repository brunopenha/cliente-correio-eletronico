package br.nom.penha.bruno.controladores;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import javafx.scene.web.WebView;

public class TelaPrincipalController {

    @FXML
    private Menu Arquivo;

    @FXML
    private MenuItem Fechar;

    @FXML
    private TableColumn<?, ?> data;

    @FXML
    private TreeView<?> cartasTreeView;

    @FXML
    private TableColumn<?, ?> origem;

    @FXML
    private Menu Edit;

    @FXML
    private Menu Ajuda;

    @FXML
    private TableView<?> cartasTableView;

    @FXML
    private TableColumn<?, ?> destinario;

    @FXML
    private TableColumn<?, ?> assunto;

    @FXML
    private TableColumn<?, ?> tamanho;

    @FXML
    private WebView cartasWebView;

    @FXML
    private MenuItem Opçoes;

    @FXML
    private MenuItem Abrir;

    @FXML
    private MenuItem Sobre;

    @FXML
    void acaoOpcoes() {

    }

}

