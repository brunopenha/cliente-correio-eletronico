package br.nom.penha.bruno.dto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class CartaTreeItem<String> extends TreeItem<String> {
    private String nome;
    private ObservableList<Mensagem> mensagens;
    private int qtdMensagensNaoLidas;

    public CartaTreeItem(String nome) {
        super(nome);
        this.nome = nome;
        this.mensagens = FXCollections.observableArrayList();
    }

    private void atualizaNome(){
        if(qtdMensagensNaoLidas > 0){
            this.setValue((String) (nome + "(" + qtdMensagensNaoLidas + ")"));
        }else {
            this.setValue(nome);
        }
    }

    public ObservableList<Mensagem> getMensagens(){
        return mensagens;
    }

    public void incrementaQtdMensagensNaoLidas(){
        qtdMensagensNaoLidas++;
        atualizaNome();
    }

    public void diminiuQtdMensagensNaoLidas(){
        qtdMensagensNaoLidas--;
        atualizaNome();
    }

    public void adicionaCarta(Message mensagemOriginal) throws MessagingException {
        mensagens.add(obtemMensagem(mensagemOriginal));
    }

    public void adicionaCartaNoInicio(Message mensagemOriginal) throws MessagingException {
        mensagens.add(0,obtemMensagem(mensagemOriginal));
    }
    private Mensagem obtemMensagem(Message mensagemOriginal) throws MessagingException {
        boolean mensagemLida = mensagemOriginal.getFlags().contains(Flags.Flag.SEEN);
        Mensagem mensagem = new Mensagem(
                mensagemOriginal.getSubject(),
                mensagemOriginal.getFrom()[0].toString(),
                mensagemOriginal.getRecipients(MimeMessage.RecipientType.TO)[0].toString(),
                mensagemOriginal.getSize(),
                mensagemOriginal.getSentDate(),
                mensagemLida,
                mensagemOriginal
        );
        if(!mensagemLida){
            incrementaQtdMensagensNaoLidas();
        }
        return mensagem;
    }


}
