package br.nom.penha.bruno.gerenciadores;

import br.nom.penha.bruno.controladores.servicos.AtualizadorPastaService;
import br.nom.penha.bruno.controladores.servicos.TrataPastaServico;
import br.nom.penha.bruno.dto.CartaTreeItem;
import br.nom.penha.bruno.dto.ContaCorreio;

import javax.mail.Folder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CorreioGerenciador {

    private AtualizadorPastaService aps;
    //Tratar pasta
    private CartaTreeItem<String> pastaRaiz = new CartaTreeItem<String>("");
    private List<Folder> listaPastas = new ArrayList<Folder>();
    private final Properties propriedadesCorreio;

    public CorreioGerenciador(){
        propriedadesCorreio = new Properties();
        try {
            propriedadesCorreio.load(getClass().getResourceAsStream("/configuracoes.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        final Integer tempoAtualizacao =
                null != propriedadesCorreio.getProperty("tempo-atualizacao")
                        && !propriedadesCorreio.getProperty("tempo-atualizacao").trim().isEmpty() ?
                        Integer.valueOf(propriedadesCorreio.getProperty("tempo-atualizacao")) : 20000;

        aps = new AtualizadorPastaService(listaPastas, tempoAtualizacao);
        aps.start();
    }


    public void adicionaContaCorreio(ContaCorreio contaAserAdicionado){
        CartaTreeItem<String> item = new CartaTreeItem<>(contaAserAdicionado.getEndereco());
        TrataPastaServico trataPastaServico = new TrataPastaServico(contaAserAdicionado.getCaixa(), item,listaPastas);
        trataPastaServico.start();
        pastaRaiz.getChildren().add(item);
    }

    public List<Folder> getListaPastas() {
        return listaPastas;
    }

    public CartaTreeItem<String> getPastaRaiz() {
        return pastaRaiz;
    }
}
