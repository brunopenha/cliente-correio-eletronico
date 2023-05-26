package br.nom.penha.bruno.dto;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.mail.Message;
import java.util.Date;

public class Mensagem {

    private SimpleStringProperty assunto;
    private SimpleStringProperty autor;
    private SimpleStringProperty destinatario;
    private SimpleIntegerProperty tamanho;
    private SimpleObjectProperty<Date> data;
    private boolean foiLido;
    private Message mensagem;

    public Mensagem(String assunto, String autor, String destinatario, int tamanho, Date data, boolean foiLido, Message mensagem) {

        this.assunto = new SimpleStringProperty(assunto);
        this.autor = new SimpleStringProperty(autor);
        this.destinatario = new SimpleStringProperty(destinatario);
        this.tamanho = new SimpleIntegerProperty(tamanho);
        this.data = new SimpleObjectProperty<Date>(data);
        this.foiLido = foiLido;
        this.mensagem = mensagem;
    }

    public String getAssunto() {
        return assunto.get();
    }

    public SimpleStringProperty assuntoProperty() {
        return assunto;
    }

    public String getAutor() {
        return autor.get();
    }

    public SimpleStringProperty autorProperty() {
        return autor;
    }

    public String getDestinatario() {
        return destinatario.get();
    }

    public SimpleStringProperty destinatarioProperty() {
        return destinatario;
    }

    public int getTamanho() {
        return tamanho.get();
    }

    public SimpleIntegerProperty tamanhoProperty() {
        return tamanho;
    }

    public Date getData() {
        return data.get();
    }

    public SimpleObjectProperty<Date> dataProperty() {
        return data;
    }

    public boolean isFoiLido() {
        return foiLido;
    }

    public Message getMensagem() {
        return mensagem;
    }

    public void setAssunto(String assunto) {
        this.assunto.set(assunto);
    }

    public void setAutor(String autor) {
        this.autor.set(autor);
    }

    public void setDestinatario(String destinatario) {
        this.destinatario.set(destinatario);
    }

    public void setTamanho(int tamanho) {
        this.tamanho.set(tamanho);
    }

    public void setData(Date data) {
        this.data.set(data);
    }

    public void setFoiLido(boolean foiLido) {
        this.foiLido = foiLido;
    }

    public void setMensagem(Message mensagem) {
        this.mensagem = mensagem;
    }
}
