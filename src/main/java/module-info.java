module cliente.correio.eletronico {

    requires java.desktop;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.web;
    requires activation;
    requires java.mail;
    // Se tiver o erro
    //Graphics Device initialization failed for :  es2, sw
    //Error initializing QuantumRenderer: no suitable pipeline found
    // Verifique a dependencia org.openjfx:javafx-graphics

    opens br.nom.penha.bruno;
    opens br.nom.penha.bruno.controladores;
    opens br.nom.penha.bruno.dto;
    exports br.nom.penha.bruno.gerenciadores;
    exports br.nom.penha.bruno.visao;
    exports br.nom.penha.bruno.controladores.servicos;
    exports br.nom.penha.bruno.dto;
}