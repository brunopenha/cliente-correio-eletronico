module cliente.correio.eletronico {

    requires java.desktop;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.web;
    // Se tiver o erro
    //Graphics Device initialization failed for :  es2, sw
    //Error initializing QuantumRenderer: no suitable pipeline found
    // Verifique a dependencia org.openjfx:javafx-graphics

    opens br.nom.penha.bruno;
    opens br.nom.penha.bruno.controladores;
}