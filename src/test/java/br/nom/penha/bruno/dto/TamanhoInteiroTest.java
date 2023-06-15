package br.nom.penha.bruno.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("EqualsWithItself")
class TamanhoInteiroTest {

    /**
     * inclua essa instrução nas Opções da VM
     * --add-opens java.base/java.lang=ALL-UNNAMED
     * Para Evitar
     * Unable to make protected final java.lang.Class java.lang.ClassLoader.defineClass(java.lang.String,byte[],int,int,java.security.ProtectionDomain) throws java.lang.ClassFormatError accessible: module java.base does not "opens java.lang" to unnamed module
     */
    @Test
    void toStringValueTest() {
        TamanhoInteiro tamanhoInteiro1 = new TamanhoInteiro(0);
        assertEquals("0",tamanhoInteiro1.toString());
        TamanhoInteiro tamanhoInteiro2 = new TamanhoInteiro(500);
        assertEquals("500 B",tamanhoInteiro2.toString());
        TamanhoInteiro tamanhoInteiro3 = new TamanhoInteiro(2050);
        assertEquals("2 KB",tamanhoInteiro3.toString());
        TamanhoInteiro tamanhoInteiro4 = new TamanhoInteiro(3321323);
        assertEquals("3 MB",tamanhoInteiro4.toString());

    }

    @Test
    void comparaResultadoTest()    {
        TamanhoInteiro tamanhoInteiro1 = new TamanhoInteiro(1000);
        TamanhoInteiro tamanhoInteiro2 = new TamanhoInteiro(2000);

        assertEquals(tamanhoInteiro1.compareTo(tamanhoInteiro2), -1);
        assertEquals(tamanhoInteiro2.compareTo(tamanhoInteiro1), 1);
        assertEquals(tamanhoInteiro2.compareTo(tamanhoInteiro2), 0);
    }
}