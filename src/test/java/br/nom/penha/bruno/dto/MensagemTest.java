package br.nom.penha.bruno.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.mail.Message;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

class MensagemTest {


    private Mensagem mensagemMock;
    private final String assunto = "qualquer assunto";
    private final String remetente = "qualquer@remetente.br";
    private final String destinatario = "qualquer@destinatario.br";
    private final Integer tamanho = 100;
    private final boolean foiLido = false;

    @Mock
    private Date dataEnvioMock;
    @Mock
    private Message messagemOriginalMock;

    /**
     * inclua essa instrução nas Opções da VM
     * --add-opens java.base/java.lang=ALL-UNNAMED
     * Para Evitar
     * Unable to make protected final java.lang.Class java.lang.ClassLoader.defineClass(java.lang.String,byte[],int,int,java.security.ProtectionDomain) throws java.lang.ClassFormatError accessible: module java.base does not "opens java.lang" to unnamed module
     */
    @BeforeEach
    void setUp() {
        initMocks(this);
        mensagemMock = new Mensagem(assunto,remetente,destinatario,tamanho,dataEnvioMock,foiLido,messagemOriginalMock);
    }

    @Test
    public void validaCampos(){
        assertEquals(remetente, mensagemMock.getAutor());
        assertEquals(assunto, mensagemMock.getAssunto());
        assertEquals(destinatario, mensagemMock.getDestinatario());
        assertEquals(new TamanhoInteiro(tamanho), mensagemMock.getTamanho());
        assertEquals(dataEnvioMock, mensagemMock.getData());
        assertEquals(foiLido, mensagemMock.isFoiLido());
        assertEquals(messagemOriginalMock, mensagemMock.getMensagem());
    }

    @Test
    public void testLeitura(){
        mensagemMock.setFoiLido(false);
        assertFalse(mensagemMock.isFoiLido());
    }
}