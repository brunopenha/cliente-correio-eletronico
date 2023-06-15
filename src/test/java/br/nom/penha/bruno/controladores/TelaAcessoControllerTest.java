package br.nom.penha.bruno.controladores;

import br.nom.penha.bruno.controladores.servicos.AcessoServico;
import br.nom.penha.bruno.gerenciadores.CorreioGerenciador;
import br.nom.penha.bruno.visao.VisaoFactory;
import javafx.application.Platform;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

class TelaAcessoControllerTest {

    private TelaAcessoController telaAcessoControlder;

    private TextField campoUsuario;
    private PasswordField campoSenha;
    private Label erroLabel;

    @Mock
    CorreioGerenciador correio;
    @Mock
    VisaoFactory visao;
    @Mock
    private AcessoServico servicoAcesso;

    @BeforeAll
    public static void inicializaFerramenta(){
        Platform.startup(() -> System.out.println("Ferramenta inicializada...."));
    }

    @BeforeEach
    void setUp() {
        initMocks(this);
        campoUsuario = new TextField();
        campoSenha = new PasswordField();
        erroLabel = new Label();
        CheckBox opcaoDebug = new CheckBox();
        telaAcessoControlder = new TelaAcessoController(correio,visao,campoUsuario,campoSenha,erroLabel, opcaoDebug,servicoAcesso,"acesso");
    }

    @Test
    public void validadorCamposTest(){
        telaAcessoControlder.acaoBotaoAcesso();
        assertEquals("Informe o email",erroLabel.getText());
        campoUsuario.setText("usuario@qualquer.br");
        telaAcessoControlder.acaoBotaoAcesso();
        assertEquals("Informe a senha",erroLabel.getText());
    }

    @Test
    public void validaAcessoTest(){
        campoUsuario.setText("usuario@qualquer.br");
        campoSenha.setText("senhaQualquer");
        telaAcessoControlder.acaoBotaoAcesso();
        verify(servicoAcesso).setConta(any());
        verify(servicoAcesso).start();
    }
}