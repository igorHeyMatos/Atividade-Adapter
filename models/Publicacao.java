
package models;

import java.util.Date;

public class Publicacao {
    private final String id;
    private final String plataforma;
    private final Conteudo conteudo;
    private final Date dataPublicacao;
    private final String status;

    public Publicacao(String id, String plataforma, Conteudo conteudo, Date dataPublicacao, String status) {
        this.id = id;
        this.plataforma = plataforma;
        this.conteudo = conteudo;
        this.dataPublicacao = dataPublicacao;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public Conteudo getConteudo() {
        return conteudo;
    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    public String getStatus() {
        return status;
    }
}
