package br.nom.penha.bruno.visao;

public enum TamanhoFonte {
    PEQUENO,
    MEDIO,
    GRANDE;

    public static String caminhoCss(TamanhoFonte tamanho){

        String escolhido;

        switch (tamanho){
            case GRANDE -> escolhido = "/css/fonteGrande.css";
            case MEDIO -> escolhido = "/css/fonteMedio.css";
            case PEQUENO -> escolhido = "/css/fontePequeno.css";
            default -> escolhido = "/css/fontePadrao.css";
        }

        return escolhido;
    }
}
