package br.nom.penha.bruno.visao;

public enum TemaCor {
    CLARO,
    PADRAO,
    ESCURO;

    public static String caminhoCss(TemaCor tema){
        String temaEscolhido;
        switch (tema){
            case CLARO -> temaEscolhido = "/css/temaClaro.css";
            case ESCURO -> temaEscolhido = "/css/temaEscuro.css";
            default -> temaEscolhido = "/css/fontePadrao.css";
        }
        
        return temaEscolhido;
    }
}
