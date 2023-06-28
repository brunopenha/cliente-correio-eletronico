package br.nom.penha.bruno.visao;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class TrataIcones {

    public Node getIconeParaPasta(String nomePasta){
        ImageView icone;

        if(nomePasta.toUpperCase().contains("INBOX")){
            icone = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/entrada.png"))));
        } else if (nomePasta.toUpperCase().contains("SENT")) {
            icone = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/enviado1.png"))));
        } else if (nomePasta.toUpperCase().contains("SPAM")) {
            icone = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/indesejados.png"))));
        } else {
            icone = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/pasta.png"))));
        }

        icone.setFitWidth(16);
        icone.setFitHeight(16);

        return icone;
    }

    public Node getIconeParaUsuario(){
        ImageView icone = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/correio.png"))));
        icone.setFitWidth(16);
        icone.setFitHeight(16);

        return icone;

    }
}
