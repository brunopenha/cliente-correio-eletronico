package br.nom.penha.bruno.controladores.servicos;

import br.nom.penha.bruno.dto.CartaTreeItem;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Store;

public class TrataPastaServico extends Service<Void> {

    private Store caixa;
    private CartaTreeItem<String> pastaRaiz;

    public TrataPastaServico(Store caixa, CartaTreeItem<String> pastaRaiz) {
        this.caixa = caixa;
        this.pastaRaiz = pastaRaiz;
    }

    @Override
    protected Task createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                trataPastas();
                return null;
            }
        };
    }

    private void trataPastas() throws MessagingException {
        Folder[] pastas = caixa.getDefaultFolder().list();
        trataPastas(pastas,pastaRaiz);
    }

    private void trataPastas(Folder[] pastas, CartaTreeItem<String> pastaRaiz) throws MessagingException {
        for (Folder pasta : pastas) {
            CartaTreeItem<String> cartaTreeItem = new CartaTreeItem<String>(pasta.getName());
            pastaRaiz.getChildren().add(cartaTreeItem);
            pastaRaiz.setExpanded(true);
            trataMensagensNaPasta(pasta,cartaTreeItem);
            if(pasta.getType() == Folder.HOLDS_FOLDERS){
                Folder[] subPastas = pasta.list();
                trataPastas(subPastas,cartaTreeItem);
            }
        }
    }

    private void trataMensagensNaPasta(Folder pasta, CartaTreeItem<String> cartaTreeItem) {
        Service obtemMensagensService = new Service() {
            @Override
            protected Task createTask() {
                return new Task() {
                    @Override
                    protected Object call() throws Exception {
                        if(pasta.getType() != Folder.HOLDS_FOLDERS){
                            pasta.open(Folder.READ_WRITE);
                            int tamanhoPasta = pasta.getMessageCount();
                            for (int i = tamanhoPasta; i > 0; i--) {
//                                System.out.println("Assuntos obtidos: " + pasta.getMessage(i).getSubject());
                                cartaTreeItem.adicionaCarta(pasta.getMessage(i));
                            }
                        }
                        return null;
                    }
                };
            }
        };
        obtemMensagensService.start();
    }
}
