package br.nom.penha.bruno.controladores.servicos;

import br.nom.penha.bruno.dto.Mensagem;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.web.WebEngine;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import java.io.IOException;

public class ExibidorMensagemServico extends Service {

    private Mensagem mensagem;
    private WebEngine motor;
    private StringBuffer sb;

    public ExibidorMensagemServico(WebEngine motor) {
        this.motor = motor;
        this.sb = new StringBuffer();
        this.setOnSucceeded(evento -> {
            exibeMensagem();
        });
    }

    private void exibeMensagem() {
        motor.loadContent(sb.toString());
    }

    public void setMensagem(Mensagem mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    protected Task createTask() {
        return new Task() {
            @Override
            protected Void call() {
                try {
                    carregaMensagem();
                } catch (MessagingException e) {
//            throw new RuntimeException(e);
                    e.printStackTrace();
                } catch (IOException e) {
//            throw new RuntimeException(e);
                    e.printStackTrace();
                }

                return null;
            }
        };

    }

    private void carregaMensagem() throws MessagingException, IOException {
        sb.setLength(0);
        Message messageFx = mensagem.getMensagem();
        if(ehMensagemSimples(messageFx.getContentType().toLowerCase())){
            sb.append(messageFx.getContent().toString());
        } else if (ehTipoNaoSimples(messageFx.getContentType().toLowerCase())) {
            Multipart multipart = (Multipart) messageFx.getContent();
            carregaMultipart(multipart,sb);
        }
    }

    private void carregaMultipart(Multipart multipart, StringBuffer sb) throws MessagingException, IOException {
        for(int i = multipart.getCount() -1;i >= 0; i--){
            BodyPart corpo = multipart.getBodyPart(i);

            if(ehMensagemSimples(corpo.getContentType().toLowerCase())){
                sb.append(corpo.getContent().toString());
            } else if (ehTipoNaoSimples(corpo.getContentType().toLowerCase())) {
                Multipart multipart1 = (Multipart) corpo.getContent();
                carregaMultipart(multipart,sb);
            } else if (ehTipoTextoPlano(corpo.getContentType().toLowerCase())) {
                MimeBodyPart mimeBodyPart = (MimeBodyPart) corpo;
                // adicione mimeBodyPart como anexo da mensagem

            }
        }
    }

    private boolean ehTipoTextoPlano(String contentType) {
        return contentType.contains("text/plain");
    }

    private boolean ehTipoNaoSimples(String tipoConteudo) {
        return tipoConteudo.contains("multipart");
    }

    private boolean ehMensagemSimples(String tipoConteudo) {
        return tipoConteudo.contains("text/html")
                || (tipoConteudo.contains("mixed") && !tipoConteudo.contains("multipart"));
//                || tipoConteudo.contains("text");
    }
}
