package br.nom.penha.bruno.controladores;
import br.nom.penha.bruno.controladores.servicos.ExibidorMensagemServico;
import br.nom.penha.bruno.dto.CartaTreeItem;
import br.nom.penha.bruno.dto.Mensagem;
import br.nom.penha.bruno.dto.TamanhoInteiro;
import br.nom.penha.bruno.gerenciadores.CorreioGerenciador;
import br.nom.penha.bruno.visao.VisaoFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebView;
import javafx.util.Callback;

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
    private TableColumn<Mensagem, TamanhoInteiro> colunaTamanho;

    @FXML
    private TableColumn<Mensagem, Date> colunaData;

    private ExibidorMensagemServico exibeMensagem;

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
        ajustaLinhasEmNegrito();
        ajustaServiceExibicaoMensagem();
        ajustaMensagemSelecionada();
    }

    private void ajustaMensagemSelecionada() {
        cartasTableView.setOnMouseClicked(evento -> {
            Mensagem mensagem = cartasTableView.getSelectionModel().getSelectedItem();
            if(null != mensagem){
                correio.setMensagemSelecionada(mensagem);
                if(!mensagem.isFoiLido()){
                    correio.setFoiLido();
                }
                exibeMensagem.setMensagem(mensagem);
                exibeMensagem.restart();
            }
        });

    }

    private void ajustaServiceExibicaoMensagem() {
        exibeMensagem = new ExibidorMensagemServico(cartasWebView.getEngine());
    }

    private void ajustaLinhasEmNegrito() {
        cartasTableView.setRowFactory(new Callback<TableView<Mensagem>, TableRow<Mensagem>>() {
            @Override
            public TableRow<Mensagem> call(TableView<Mensagem> mensagemTableView) {
                return new TableRow<Mensagem>(){
                    @Override
                    protected void updateItem(Mensagem mensagem, boolean b) {
                        super.updateItem(mensagem, b);
                        if(null != mensagem){
                            if(getItem().isFoiLido()){
                                setStyle("");
                            }else{
                                setStyle("-fx-font-weight: bold");
                            }
                        }
                    }
                };
            }
        });
    }

    private void ajustaPastaSelecionada() {
        cartasTreeView.setOnMouseClicked(e -> {
            CartaTreeItem<String> item = (CartaTreeItem<String>) cartasTreeView.getSelectionModel().getSelectedItem();
            if(null != item){
                correio.setPastaSelecionada(item);
                cartasTableView.setItems(item.getMensagens());
            }
        });
    }

    private void ajustaVisualizacaoListaMensagens() {
        colunaOrigem.setCellValueFactory(new PropertyValueFactory<Mensagem,String>("autor"));
        colunaAssunto.setCellValueFactory(new PropertyValueFactory<Mensagem,String>("assunto"));
        colunaDestinario.setCellValueFactory(new PropertyValueFactory<Mensagem,String>("destinatario"));
        colunaTamanho.setCellValueFactory(new PropertyValueFactory<Mensagem,TamanhoInteiro>("tamanho"));
        colunaData.setCellValueFactory(new PropertyValueFactory<Mensagem,Date>("data"));
    }

    private void ajustaCartasVisaoArvore() {
        cartasTreeView.setRoot(correio.getPastaRaiz());
        cartasTreeView.setShowRoot(false);
    }
}

