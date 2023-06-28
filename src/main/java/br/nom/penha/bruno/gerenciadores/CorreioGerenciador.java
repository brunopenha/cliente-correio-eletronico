package br.nom.penha.bruno.gerenciadores;

import br.nom.penha.bruno.controladores.servicos.AtualizadorPastaService;
import br.nom.penha.bruno.controladores.servicos.TrataPastaServico;
import br.nom.penha.bruno.dto.CartaTreeItem;
import br.nom.penha.bruno.dto.ContaCorreio;
import br.nom.penha.bruno.dto.Mensagem;
import br.nom.penha.bruno.visao.TrataIcones;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CorreioGerenciador {

    private Mensagem mensagemSelecionada;
    private CartaTreeItem pastaSelecionada;

    private AtualizadorPastaService aps;
    //Tratar pasta
    private CartaTreeItem<String> pastaRaiz = new CartaTreeItem<String>("");
    private List<Folder> listaPastas = new ArrayList<Folder>();
    private final Properties propriedadesCorreio;
    private ObservableList<ContaCorreio> contas = FXCollections.observableArrayList();
    private TrataIcones icones = new TrataIcones();

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
        item.setGraphic(icones.getIconeParaUsuario());
        TrataPastaServico trataPastaServico = new TrataPastaServico(contaAserAdicionado.getCaixa(), item,listaPastas);
        trataPastaServico.start();
        pastaRaiz.getChildren().add(item);
        contas.add(contaAserAdicionado);
    }

    public List<Folder> getListaPastas() {
        return listaPastas;
    }

    public CartaTreeItem<String> getPastaRaiz() {
        return pastaRaiz;
    }

    public Mensagem getMensagemSelecionada() {
        return mensagemSelecionada;
    }

    public void setMensagemSelecionada(Mensagem mensagemSelecionada) {
        this.mensagemSelecionada = mensagemSelecionada;
    }

    public CartaTreeItem getPastaSelecionada() {
        return pastaSelecionada;
    }

    public void setPastaSelecionada(CartaTreeItem pastaSelecionada) {
        this.pastaSelecionada = pastaSelecionada;
    }

    public void setFoiLido() {
        //Aqui ja sabemos qual mensagem foi selecionada
        try {
            mensagemSelecionada.setFoiLido(true);
            mensagemSelecionada.getMensagem().setFlag(Flags.Flag.SEEN, true);
            pastaSelecionada.diminiuQtdMensagensNaoLidas();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void setNaoFoiLido() {
        //Aqui ja sabemos qual mensagem foi selecionada
        try {
            mensagemSelecionada.setFoiLido(false);
            mensagemSelecionada.getMensagem().setFlag(Flags.Flag.SEEN, false);
            pastaSelecionada.incrementaQtdMensagensNaoLidas();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<ContaCorreio> getContas() {
        return contas;
    }

    public void apagaMensagemSelecionada() {
        try {
            mensagemSelecionada.getMensagem().setFlag(Flags.Flag.DELETED, true);
            pastaSelecionada.getMensagens().remove(mensagemSelecionada);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
