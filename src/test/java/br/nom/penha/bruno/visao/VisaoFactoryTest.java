package br.nom.penha.bruno.visao;

import br.nom.penha.bruno.Inicializador;
import br.nom.penha.bruno.controladores.BaseController;
import br.nom.penha.bruno.controladores.TelaAcessoController;
import br.nom.penha.bruno.gerenciadores.CorreioGerenciador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

class VisaoFactoryTest {

    private VisaoFactory visao;
    @Mock
    private CorreioGerenciador gerenciadorMock;
    @Mock
    private Inicializador inicializadorMock;

    @BeforeEach
    void setUp() {
        initMocks(this);
        visao = new VisaoFactory(gerenciadorMock);
    }

    @Test
    public void exibeTelaAcessoTest() throws Exception {
        BaseController telaAcessoController = new TelaAcessoController(gerenciadorMock,visao,"acesso.fxml");
        visao.exibeTelaAcesso();
        verify(inicializadorMock).init();
    }
}