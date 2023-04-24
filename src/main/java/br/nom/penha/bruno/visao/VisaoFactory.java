package br.nom.penha.bruno.visao;

import br.nom.penha.bruno.controladores.BaseController;
import br.nom.penha.bruno.controladores.TelaAcessoController;
import br.nom.penha.bruno.controladores.TelaPrincipalController;
import br.nom.penha.bruno.gerenciadores.CorreioManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class VisaoFactory {

    private CorreioManager correio;

    public VisaoFactory(CorreioManager correio) {
        this.correio = correio;
    }

    private static void inicializaPalco(FXMLLoader fxmlLoader) {
        Parent pai;
        try {
            pai = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene cena = new Scene(pai);
        Stage palco = new Stage();
        palco.setScene(cena);
        palco.show();
    }

    public void exibeTelaAcesso() {

        BaseController controlador = new TelaAcessoController(correio, this, "/telas/acesso/acesso.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(controlador.getNomeArquivoFxml()));
        fxmlLoader.setController(controlador);

        inicializaPalco(fxmlLoader);
    }

    public void exibeTelaCorreio() {

        BaseController controlador = new TelaPrincipalController(correio, this, "/telas/correio/principal.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(controlador.getNomeArquivoFxml()));
        fxmlLoader.setController(controlador);

        inicializaPalco(fxmlLoader);
    }

    public void encerraPalco(Stage palcoASerEncerrado) {
        palcoASerEncerrado.close();
    }
}
