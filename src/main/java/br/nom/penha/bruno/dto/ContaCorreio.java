package br.nom.penha.bruno.dto;

import javax.mail.Store;
import java.io.IOException;
import java.util.Properties;

public class ContaCorreio {
    private String endereco;
    private String senha;
    private Properties propriedades;
    private Store caixa;

    public ContaCorreio(String endereco, String senha) {
        this.endereco = endereco;
        this.senha = senha;
        this.propriedades = new Properties();

        try {
            this.propriedades.load(getClass().getResourceAsStream("/configuracoes.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

       //propriedades.put("mail.debug", "true");

        propriedades.put("entrada",propriedades.getProperty("servidor"));
        propriedades.put("mail.store.protocol",propriedades.getProperty("protocolo.entrada"));
        propriedades.put("mail.imaps.ssl.trust", "*");
        propriedades.put("mail.imaps.starttls.enable",true);

        propriedades.put("saida",propriedades.getProperty("servidor"));
        propriedades.put("mail.transport.protocol",propriedades.getProperty("protocolo.saida"));
        propriedades.put("mail.smtp.host",propriedades.getProperty("servidor"));
        propriedades.put("mail.smtp.auth","true");
        propriedades.put("mail.smtp.ssl.trust", "*");
    }

    public String getEndereco() {
        return endereco;
    }

    public String getSenha() {
        return senha;
    }

    public Properties getPropriedades() {
        return propriedades;
    }

    public Store getCaixa() {
        return caixa;
    }

    public void setPropriedades(Properties propriedades) {
        this.propriedades = propriedades;
    }

    public void setCaixa(Store caixa) {
        this.caixa = caixa;
    }
}
