package br.nom.penha.bruno.controladores;

import br.nom.penha.bruno.gerenciadores.CorreioManager;
import br.nom.penha.bruno.visao.VisaoFactory;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;

public class TelaOpcoesController extends BaseController{

    @FXML
    private Slider selecionadorTamFonte;

    @FXML
    private ChoiceBox<?> selecionadorTema;
    

    public TelaOpcoesController(CorreioManager correio, VisaoFactory visao, String nomeArquivoFxmlParam) {
        super(correio, visao, nomeArquivoFxmlParam);
    }

    @FXML
    void acaoBotaoAplicar() {

    }

    @FXML
    void acaoBotaoCancelar() {

    }
}
