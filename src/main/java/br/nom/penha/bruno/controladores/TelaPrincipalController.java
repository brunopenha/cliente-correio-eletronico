package br.nom.penha.bruno.controladores;
import br.nom.penha.bruno.gerenciadores.CorreioGerenciador;
import br.nom.penha.bruno.visao.VisaoFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class TelaPrincipalController extends BaseController implements Initializable {

    @FXML
    private TreeView<String> cartasTreeView;

    @FXML
    private TableView<?> cartasTableView;

    @FXML
    private WebView cartasWebView;

    @FXML
    private MenuItem adicionaConta;

    public TelaPrincipalController(CorreioGerenciador correio, VisaoFactory visao, String nomeArquivoFxml) {
        super(correio, visao, nomeArquivoFxml);
    }


    @FXML
    void acaoOpcoes() {
        visao.exibeTelaOpcoes();
    }


    @FXML
    void acaoAdicionaConta() {
        visao.exibeTelaAcesso();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ajustaCartasVisaoArvore();
    }

    private void ajustaCartasVisaoArvore() {
        cartasTreeView.setRoot(correio.getPastaRaiz());
        cartasTreeView.setShowRoot(false);
    }
}

