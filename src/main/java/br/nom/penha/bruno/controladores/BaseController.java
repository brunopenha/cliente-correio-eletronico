package br.nom.penha.bruno.controladores;

import br.nom.penha.bruno.gerenciadores.CorreioGerenciador;
import br.nom.penha.bruno.visao.VisaoFactory;

public abstract class BaseController {

    VisaoFactory visao;
    CorreioGerenciador correio;
    private String nomeArquivoFxml;

    public BaseController(CorreioGerenciador correio, VisaoFactory visao, String nomeArquivoFxmlParam) {
        this.correio = correio;
        this.visao = visao;
        this.nomeArquivoFxml = nomeArquivoFxmlParam;
    }

    public String getNomeArquivoFxml() {
        return nomeArquivoFxml;
    }
}
