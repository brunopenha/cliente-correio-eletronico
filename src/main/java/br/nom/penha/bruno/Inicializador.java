package br.nom.penha.bruno;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Inicializador extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        Parent pai = FXMLLoader.load(getClass().getResource("/telas/acesso/acesso.fxml"));

        Scene scn = new Scene(pai, 600, 400);

        stage.setScene(scn);
        stage.show();
    }
}
