package br.nom.penha.bruno;

import br.nom.penha.bruno.gerenciadores.CorreioGerenciador;
import br.nom.penha.bruno.visao.VisaoFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class Inicializador extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
////        Parent pai = FXMLLoader.load(getClass().getResource("/telas/acesso/acesso.fxml"));
//        Parent paiPrincipal = FXMLLoader.load(getClass().getResource("/telas/correio/principal.fxml"));
//
////        Scene scn = new Scene(pai, 600, 400);
//        Scene scn = new Scene(paiPrincipal);
//
//        stage.setScene(scn);
//        stage.show();

        VisaoFactory visaoFactory = new VisaoFactory(new CorreioGerenciador());
        visaoFactory.exibeTelaAcesso();
    }
}
