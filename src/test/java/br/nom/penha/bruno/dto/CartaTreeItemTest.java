package br.nom.penha.bruno.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class CartaTreeItemTest {

    private CartaTreeItem<String> cartaTreeItem;
    @Mock
    private Message mensagemOriginalMock;
    @Mock
    private Flags flagsMock;
    @Mock
    private Address destinatarioMock;
    @Mock
    private Address remetenteMock;
    @Mock
    private Date dataEnvioMock;

    /**
     * inclua essa instrução nas Opções da VM
     * --add-opens java.base/java.lang=ALL-UNNAMED
     * Para Evitar
     * Unable to make protected final java.lang.Class java.lang.ClassLoader.defineClass(java.lang.String,byte[],int,int,java.security.ProtectionDomain) throws java.lang.ClassFormatError accessible: module java.base does not "opens java.lang" to unnamed module
     */
    @BeforeEach
    void setUp() throws MessagingException {
        initMocks(this);
        cartaTreeItem = new CartaTreeItem<>("Entrada");
        when(mensagemOriginalMock.getFlags()).thenReturn(flagsMock);
        Address[] listaDestinatarios = {destinatarioMock};
        when(mensagemOriginalMock.getRecipients(MimeMessage.RecipientType.TO)).thenReturn(listaDestinatarios);
        when(mensagemOriginalMock.getSubject()).thenReturn("Assunto Qualquer");
        when(remetenteMock.toString()).thenReturn("algum@autor.br");
        Address[] listaRemetentes = {remetenteMock};
        when(mensagemOriginalMock.getFrom()).thenReturn(listaRemetentes);
        when(destinatarioMock.toString()).thenReturn("algum@destinatario.br");
        when(mensagemOriginalMock.getSize()).thenReturn(1000);
        when(mensagemOriginalMock.getSentDate()).thenReturn(dataEnvioMock);
    }

    @Test
    public void adicionaCartaTest() throws MessagingException {
        cartaTreeItem.adicionaCarta(mensagemOriginalMock);
        assertEquals(1,cartaTreeItem.getMensagens().size());
        assertEquals("Entrada(1)",cartaTreeItem.getValue());

        Mensagem carta = cartaTreeItem.getMensagens().get(0);
        assertEquals("Assunto Qualquer",carta.getAssunto());
        assertEquals("algum@autor.br", carta.getAutor());
        assertEquals("algum@destinatario.br",carta.getDestinatario());
        assertEquals(new TamanhoInteiro(1000),carta.getTamanho());
        assertEquals(dataEnvioMock, carta.getData());
        assertEquals(mensagemOriginalMock,carta.getMensagem());

    }

    @Test
    public void adicionaEmailNaoLido() throws MessagingException {
        cartaTreeItem.adicionaCarta(mensagemOriginalMock);
        Mensagem carta = cartaTreeItem.getMensagens().get(0);
        assertFalse(carta.isFoiLido());
        assertEquals("Entrada(1)",cartaTreeItem.getValue());
        cartaTreeItem.diminiuQtdMensagensNaoLidas();
        assertEquals("Entrada",cartaTreeItem.getValue());

    }
}