package br.nom.penha.bruno.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.mail.Store;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

class ContaCorreioTest {

    private ContaCorreio conta;
    private final String enderecoQualquer = "algum@correio.br";
    private final String senhaQualquer = "qualquerSenha";
    private Properties propriedades;

    /**
     * add this to VM Options
     * --add-opens java.base/java.lang=ALL-UNNAMED
     * To avoid
     * Unable to make protected final java.lang.Class java.lang.ClassLoader.defineClass(java.lang.String,byte[],int,int,java.security.ProtectionDomain) throws java.lang.ClassFormatError accessible: module java.base does not "opens java.lang" to unnamed module
     */
    @Mock
    private Store caixaMock;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        propriedades = new Properties();
        conta = new ContaCorreio(enderecoQualquer,senhaQualquer,false);
    }

    @Test
    public void validaCampos(){
        assertEquals(conta.getEndereco(), enderecoQualquer);
        assertEquals(conta.getSenha(), senhaQualquer);
    }

    @Test
    public void validaPropriedades(){
        conta.setPropriedades(propriedades);
        assertEquals(conta.getPropriedades(),propriedades);
        conta.setCaixa(caixaMock);
        assertEquals(conta.getCaixa(),caixaMock);
    }
}