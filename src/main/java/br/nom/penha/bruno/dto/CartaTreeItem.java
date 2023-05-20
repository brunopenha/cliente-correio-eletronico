package br.nom.penha.bruno.dto;

import javafx.scene.control.TreeItem;

public class CartaTreeItem<String> extends TreeItem<String> {
    private String nome;

    public CartaTreeItem(String nome) {
        super(nome);
        this.nome = nome;
    }
}
