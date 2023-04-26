package br.nom.penha.bruno.controladores;
import br.nom.penha.bruno.gerenciadores.CorreioManager;
import br.nom.penha.bruno.visao.VisaoFactory;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import javafx.scene.web.WebView;

public class TelaPrincipalController extends BaseController {

    @FXML
    private TreeView<?> cartasTreeView;

    @FXML
    private TableView<?> cartasTableView;

    @FXML
    private WebView cartasWebView;

    @FXML
    private MenuItem adicionaConta;

    public TelaPrincipalController(CorreioManager correio, VisaoFactory visao, String nomeArquivoFxml) {
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

}

