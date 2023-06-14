package br.nom.penha.bruno.dto;

import java.util.Objects;

public class TamanhoInteiro implements Comparable<TamanhoInteiro>{

    private int tamanho;

    public TamanhoInteiro(int tamanho) {
        this.tamanho = tamanho;
    }

    @Override
    public String toString() {

        if (tamanho <= 0){
            return "0";
        } else if (tamanho < 1024) {
            return tamanho + " B";
        } else if (tamanho < (1024 * 1024)) {
            return tamanho / 1024 + " KB";
        } else if (tamanho < (1024 * 1024 * 1024)) {
            return tamanho / (1024 * 1024) + " MB";
        }
        return "-";
    }

    @Override
    public int compareTo(TamanhoInteiro o) {
        if (tamanho > o.tamanho){
            return 1;
        } else if (tamanho < o.tamanho) {
            return -1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TamanhoInteiro that)) return false;
        return tamanho == that.tamanho;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tamanho);
    }
}
