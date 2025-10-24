
package models;

import java.util.List;

public class Conteudo {
    private final String texto;
    private final String midiaUrl;
    private final List<String> hashtags;

    public Conteudo(String texto, String midiaUrl, List<String> hashtags) {
        this.texto = texto;
        this.midiaUrl = midiaUrl;
        this.hashtags = hashtags;
    }

    public String getTexto() {
        return texto;
    }

    public String getMidiaUrl() {
        return midiaUrl;
    }

    public java.util.List<String> getHashtags() {
        return hashtags;
    }
}
