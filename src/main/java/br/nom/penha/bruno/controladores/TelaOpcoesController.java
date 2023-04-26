package br.nom.penha.bruno.controladores;

import br.nom.penha.bruno.gerenciadores.CorreioManager;
import br.nom.penha.bruno.visao.TamanhoFonte;
import br.nom.penha.bruno.visao.TemaCor;
import br.nom.penha.bruno.visao.VisaoFactory;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class TelaOpcoesController extends BaseController implements Initializable {

    @FXML
    private Slider selecionadorTamFonte;

    @FXML
    private ChoiceBox<TemaCor> selecionadorTema;
    

    public TelaOpcoesController(CorreioManager correio, VisaoFactory visao, String nomeArquivoFxmlParam) {
        super(correio, visao, nomeArquivoFxmlParam);
    }

    @FXML
    void acaoBotaoAplicar() {
        visao.setCor(selecionadorTema.getValue());
        visao.setTamanhoFonte(TamanhoFonte.values()[(int) selecionadorTamFonte.getValue()]);
        visao.atualizaEstilos();
    }

    @FXML
    void acaoBotaoCancelar() {
        visao.fechaTela((Stage) selecionadorTamFonte.getScene().getWindow());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ajustaSelecionadorTemas();
        ajustaSelecionadorTamFonte();
    }

    private void ajustaSelecionadorTamFonte() {
        selecionadorTamFonte.setMin(0);
        selecionadorTamFonte.setMax(TamanhoFonte.values().length -1);
        selecionadorTamFonte.setValue(visao.getTamanhoFonte().ordinal());
        selecionadorTamFonte.setMajorTickUnit(1);
        selecionadorTamFonte.setMinorTickCount(0);
        selecionadorTamFonte.setBlockIncrement(1);
        selecionadorTamFonte.setSnapToTicks(true);
        selecionadorTamFonte.setShowTickLabels(true);
        selecionadorTamFonte.setShowTickLabels(true);
        selecionadorTamFonte.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double aDouble) {
                return TamanhoFonte.values()[aDouble.intValue()].toString();
            }

            @Override
            public Double fromString(String s) {
                return null;
            }
        });

        // Isso irá fazer com que seja selecionado o valor sem precisar deslizar
        selecionadorTamFonte.valueProperty().addListener((obs, valorAntigo, valorNovo) -> {
            selecionadorTamFonte.setValue(valorNovo.intValue());
        });
    }

    private void ajustaSelecionadorTemas() {
        selecionadorTema.setItems(FXCollections.observableArrayList(TemaCor.values()));
        selecionadorTema.setValue(visao.getCor());
    }
}
