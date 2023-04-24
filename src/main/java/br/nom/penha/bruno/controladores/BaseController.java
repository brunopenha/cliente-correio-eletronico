package br.nom.penha.bruno.controladores;

import br.nom.penha.bruno.gerenciadores.CorreioManager;
import br.nom.penha.bruno.visao.VisaoFactory;

public abstract class BaseController {

    VisaoFactory visao;
    private CorreioManager correio;
    private String nomeArquivoFxml;

    public BaseController(CorreioManager correio, VisaoFactory visao, String nomeArquivoFxmlParam) {
        this.correio = correio;
        this.visao = visao;
        this.nomeArquivoFxml = nomeArquivoFxmlParam;
    }

    public String getNomeArquivoFxml() {
        return nomeArquivoFxml;
    }
}
