package br.nom.penha.bruno.gerenciadores;

import br.nom.penha.bruno.controladores.servicos.TrataPastaServico;
import br.nom.penha.bruno.dto.CartaTreeItem;
import br.nom.penha.bruno.dto.ContaCorreio;

public class CorreioGerenciador {

    //Tratar pasta
    private CartaTreeItem<String> pastaRaiz = new CartaTreeItem<String>("");

    public void adicionaContaCorreio(ContaCorreio contaAserAdicionado){
        CartaTreeItem<String> item = new CartaTreeItem<>(contaAserAdicionado.getEndereco());
        TrataPastaServico trataPastaServico = new TrataPastaServico(contaAserAdicionado.getCaixa(), item);
        trataPastaServico.start();
        pastaRaiz.getChildren().add(item);
    }

    public CartaTreeItem<String> getPastaRaiz() {
        return pastaRaiz;
    }
}
