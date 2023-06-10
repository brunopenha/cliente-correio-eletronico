package br.nom.penha.bruno.visao;

import br.nom.penha.bruno.controladores.BaseController;
import br.nom.penha.bruno.controladores.CriaCartaController;
import br.nom.penha.bruno.controladores.TelaAcessoController;
import br.nom.penha.bruno.controladores.TelaOpcoesController;
import br.nom.penha.bruno.controladores.TelaPrincipalController;
import br.nom.penha.bruno.gerenciadores.CorreioGerenciador;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VisaoFactory {

    private CorreioGerenciador correio;
    private List<Stage> telasAtivas;

    // Opcoes de visualização
    private TemaCor cor = TemaCor.PADRAO;
    private TamanhoFonte tamanhoFonte = TamanhoFonte.MEDIO;
    private boolean telaPrincipalInicializada = false;

    public VisaoFactory(CorreioGerenciador correio) {
        this.correio = correio;
        this.telasAtivas = new ArrayList<Stage>();
    }

    private void inicializaPalco(BaseController controlador) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(controlador.getNomeArquivoFxml()));
        fxmlLoader.setController(controlador);

        Parent pai;
        try {
            pai = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene cena = new Scene(pai);
        Stage tela = new Stage();
        tela.setScene(cena);
        tela.show();
        telasAtivas.add(tela);
    }

    public void exibeTelaAcesso() {

        inicializaPalco(new TelaAcessoController(correio, this, "/telas/acesso/acesso.fxml"));
    }

    public void exibeTelaCorreio() {
        telaPrincipalInicializada=true;
        inicializaPalco(new TelaPrincipalController(correio, this, "/telas/correio/principal.fxml"));
    }

    public void fechaTela(Stage telaAserFechada) {
        telaAserFechada.close();
        telasAtivas.remove(telaAserFechada);
    }

    public void exibeTelaOpcoes(){
        inicializaPalco(new TelaOpcoesController(correio, this, "/telas/opcoes/opcoes.fxml"));
    }

    public void exibeTelaCriaMensagem(){
        inicializaPalco(new CriaCartaController(correio, this, "/telas/correio/cria_carta.fxml"));
    }

    public void atualizaEstilos() {

        for (Stage tela : telasAtivas) {
            Scene cena = tela.getScene();
            // Vamos brincar com o css
            cena.getStylesheets().clear();
            cena.getStylesheets().add(getClass().getResource(TemaCor.caminhoCss(cor)).toExternalForm());
            cena.getStylesheets().add(getClass().getResource(TamanhoFonte.caminhoCss(tamanhoFonte)).toExternalForm());
        }
    }

    public TemaCor getCor() {
        return cor;
    }

    public void setCor(TemaCor cor) {
        this.cor = cor;
    }

    public TamanhoFonte getTamanhoFonte() {
        return tamanhoFonte;
    }

    public void setTamanhoFonte(TamanhoFonte tamanhoFonte) {
        this.tamanhoFonte = tamanhoFonte;
    }

    public boolean isTelaPrincipalInicializada(){
        return telaPrincipalInicializada;
    }
}
