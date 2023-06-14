package br.nom.penha.bruno.dto;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.mail.Message;
import java.util.Date;
import java.util.Objects;

public class Mensagem {

    private SimpleStringProperty assunto;
    private SimpleStringProperty autor;
    private SimpleStringProperty destinatario;
    private SimpleObjectProperty<TamanhoInteiro> tamanho;
    private SimpleObjectProperty<Date> data;
    private boolean foiLido;
    private Message mensagem;

    public Mensagem(String assunto, String autor, String destinatario, int tamanho, Date data, boolean foiLido, Message mensagem) {

        this.assunto = new SimpleStringProperty(assunto);
        this.autor = new SimpleStringProperty(autor);
        this.destinatario = new SimpleStringProperty(destinatario);
        this.tamanho = new SimpleObjectProperty<TamanhoInteiro>(new TamanhoInteiro(tamanho));
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

    public TamanhoInteiro getTamanho() {
        return tamanho.get();
    }

    public SimpleObjectProperty<TamanhoInteiro> tamanhoProperty() {
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
        this.tamanho.set(new TamanhoInteiro(tamanho));
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mensagem mensagem1)) return false;
        return isFoiLido() == mensagem1.isFoiLido() &&
                Objects.equals(getAssunto(), mensagem1.getAssunto()) &&
                Objects.equals(getAutor(), mensagem1.getAutor()) &&
                Objects.equals(getDestinatario(), mensagem1.getDestinatario()) &&
                Objects.equals(getTamanho(), mensagem1.getTamanho()) &&
                Objects.equals(getData(), mensagem1.getData()) &&
                Objects.equals(getMensagem(), mensagem1.getMensagem());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAssunto(), getAutor(), getDestinatario(), getTamanho(), getData(), isFoiLido(), getMensagem());
    }
}
