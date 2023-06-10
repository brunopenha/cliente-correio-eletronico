package br.nom.penha.bruno.controladores.servicos;

import br.nom.penha.bruno.controladores.CartaEnviadaResultado;
import br.nom.penha.bruno.dto.ContaCorreio;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EnviaCartaServico extends Service<CartaEnviadaResultado> {

    private ContaCorreio conta;
    private String assunto;
    private String destinatario;
    private String conteudo;

    public EnviaCartaServico(ContaCorreio conta, String assunto, String destinatario, String conteudo) {
        this.conta = conta;
        this.assunto = assunto;
        this.destinatario = destinatario;
        this.conteudo = conteudo;
    }

    @Override
    protected Task<CartaEnviadaResultado> createTask() {
        return new Task<CartaEnviadaResultado>() {
            @Override
            protected CartaEnviadaResultado call() throws Exception {
                try {
                    MimeMessage mensagem = new MimeMessage(conta.getSessao());
                    mensagem.setFrom(conta.getEndereco());
                    mensagem.addRecipients(Message.RecipientType.TO, destinatario);
                    mensagem.setSubject(assunto);

                    Multipart multipart = new MimeMultipart();
                    BodyPart corpo = new MimeBodyPart();
                    corpo.setContent(conteudo,"text/html");
                    multipart.addBodyPart(corpo);
                    mensagem.setContent(multipart);

                    Transport transport = conta.getSessao().getTransport();

                    transport.connect(
                        conta.getPropriedades().getProperty("servidor.envio"),
                        conta.getEndereco(),
                        conta.getSenha()
                    );

                    transport.sendMessage(mensagem,mensagem.getAllRecipients());
                    transport.close();

                    return CartaEnviadaResultado.SUCESSO;
                } catch (MessagingException e){
                    e.printStackTrace();
                    return CartaEnviadaResultado.FALHOU_POR_SERVIDOR;
                } catch (Exception e){
                    e.printStackTrace();
                    return CartaEnviadaResultado.FALOU_POR_MOTIVO_DESCONHECIDO;
                }

            }
        };
    }
}
