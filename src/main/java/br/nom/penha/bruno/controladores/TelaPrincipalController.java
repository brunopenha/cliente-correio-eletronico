package br.nom.penha.bruno.controladores;
import br.nom.penha.bruno.dto.CartaTreeItem;
import br.nom.penha.bruno.dto.Mensagem;
import br.nom.penha.bruno.gerenciadores.CorreioGerenciador;
import br.nom.penha.bruno.visao.VisaoFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class TelaPrincipalController extends BaseController implements Initializable {

    @FXML
    private TreeView<String> cartasTreeView;

    @FXML
    private TableView<Mensagem> cartasTableView;

    @FXML
    private WebView cartasWebView;

    @FXML
    private MenuItem adicionaConta;


    @FXML
    private TableColumn<Mensagem, String> colunaOrigem;

    @FXML
    private TableColumn<Mensagem, String> colunaDestinario;

    @FXML
    private TableColumn<Mensagem, String> colunaAssunto;

    @FXML
    private TableColumn<Mensagem, Integer> colunaTamanho;

    @FXML
    private TableColumn<Mensagem, Date> colunaData;

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
        ajustaVisualizacaoListaMensagens();
        ajustaPastaSelecionada();
    }

    private void ajustaPastaSelecionada() {
        cartasTreeView.setOnMouseClicked(e -> {
            CartaTreeItem<String> item = (CartaTreeItem<String>) cartasTreeView.getSelectionModel().getSelectedItem();
            if(null != item){
                cartasTableView.setItems(item.getMensagens());
            }
        });
    }

    private void ajustaVisualizacaoListaMensagens() {
        colunaOrigem.setCellValueFactory(new PropertyValueFactory<Mensagem,String>("autor"));
        colunaAssunto.setCellValueFactory(new PropertyValueFactory<Mensagem,String>("assunto"));
        colunaDestinario.setCellValueFactory(new PropertyValueFactory<Mensagem,String>("destinatario"));
        colunaTamanho.setCellValueFactory(new PropertyValueFactory<Mensagem,Integer>("tamanho"));
        colunaData.setCellValueFactory(new PropertyValueFactory<Mensagem,Date>("data"));
    }

    private void ajustaCartasVisaoArvore() {
        cartasTreeView.setRoot(correio.getPastaRaiz());
        cartasTreeView.setShowRoot(false);
    }
}

