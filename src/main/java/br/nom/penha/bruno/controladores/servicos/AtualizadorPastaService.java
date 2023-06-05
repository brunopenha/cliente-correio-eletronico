package br.nom.penha.bruno.controladores.servicos;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.mail.Folder;
import java.util.List;

public class AtualizadorPastaService extends Service {

    private List<Folder> listaPastas;
    private Integer intervaloAtualizacao;

    public AtualizadorPastaService(List<Folder> listaPastasParam, Integer intervaloAtualizacaoParam) {
        this.listaPastas = listaPastasParam;
        this.intervaloAtualizacao = intervaloAtualizacaoParam;
    }

    @Override
    protected Task createTask() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                // Aqui o serviço ficará rodando enquanto o programa estiver rodando
                boolean executando = true;
                while(executando){
                    try {
                        Thread.sleep(intervaloAtualizacao);
                        for (Folder pasta: listaPastas) {
                            if(pasta.getType() != Folder.HOLDS_FOLDERS
                                    && pasta.isOpen()){
                                pasta.getMessageCount();
                            }
                        }
                    } catch (InterruptedException e) {
                        executando=false;
                        e.printStackTrace();
                    }

                }
                return null;
            }
        };
    }
}
