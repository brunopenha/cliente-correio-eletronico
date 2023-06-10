package br.nom.penha.bruno.controladores;

import br.nom.penha.bruno.controladores.servicos.EnviaCartaServico;
import br.nom.penha.bruno.dto.ContaCorreio;
import br.nom.penha.bruno.gerenciadores.CorreioGerenciador;
import br.nom.penha.bruno.visao.VisaoFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CriaCartaController extends BaseController implements Initializable {

    @FXML
    private Button botaoEnviar;

    @FXML
    private TextField campoAssunto;

    @FXML
    private Label erroLabel;

    @FXML
    private TextField campoDestinatario;

    @FXML
    private HTMLEditor editorHTML;

    @FXML
    private ChoiceBox<ContaCorreio> opcaoConta;

    public CriaCartaController(CorreioGerenciador correio, VisaoFactory visao, String nomeArquivoFxmlParam) {
        super(correio, visao, nomeArquivoFxmlParam);
    }

    @FXML
    void acaoBotaoEnviar() {
        EnviaCartaServico servico = new EnviaCartaServico(
                opcaoConta.getValue(),
                campoAssunto.getText(),
                campoDestinatario.getText(),
                editorHTML.getHtmlText());

        servico.start();
        servico.setOnSucceeded(evento -> {
            CartaEnviadaResultado resultado = servico.getValue();
            switch (resultado){
                case SUCESSO -> {
                    erroLabel.setText("");
                    visao.fechaTela((Stage) campoDestinatario.getScene().getWindow());
                }
                case FALHOU_POR_SERVIDOR -> {
                    erroLabel.setText("Error no Servidor");
                }
                case FALOU_POR_MOTIVO_DESCONHECIDO -> {
                    erroLabel.setText("Error não esperado");
                }
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        opcaoConta.setItems(correio.getContas());
        opcaoConta.setValue(correio.getContas().get(0));
    }
}
