
package adapter;

import interfaces.GerenciadorMidiaSocial;
import models.*;
import api.LinkedinAPI;

import java.util.Calendar;
import java.util.Date;

public class LinkedinAdapter implements GerenciadorMidiaSocial {
    private final LinkedinAPI api;

    public LinkedinAdapter(LinkedinAPI api) {
        this.api = api;
    }

    @Override
    public Resposta<Publicacao> publicarConteudo(Conteudo conteudo) {
        try {
            String titulo = extrairTitulo(conteudo.getTexto());
            String id = api.compartilharArtigo(titulo, conteudo.getTexto(), conteudo.getMidiaUrl());
            Publicacao p = new Publicacao(id, getNomePlataforma(), conteudo, new Date(), "PUBLICADO");
            return Resposta.sucesso(p);
        } catch (Exception e) {
            return Resposta.erro("Erro LinkedIn publicar: " + e.getMessage(), "LINKEDIN_001");
        }
    }

    @Override
    public Resposta<Boolean> agendarPublicacao(Conteudo conteudo, Date dataAgendamento) {
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(dataAgendamento);
            boolean ok = api.agendarPost(extrairTitulo(conteudo.getTexto()), conteudo.getTexto(),
                    conteudo.getMidiaUrl(), c);
            return Resposta.sucesso(ok);
        } catch (Exception e) {
            return Resposta.erro("Erro agendar LinkedIn: " + e.getMessage(), "LINKEDIN_002");
        }
    }

    @Override
    public Resposta<Estatisticas> obterEstatisticas(String idPublicacao) {
        try {
            LinkedinAPI.EstatisticasLinkedin s = api.obterAnalytics(idPublicacao);
            Estatisticas e = new Estatisticas(s.getCurtidas(), 0, s.getComentarios(), s.getImpressoes());
            return Resposta.sucesso(e);
        } catch (Exception e) {
            return Resposta.erro("Erro analytics LinkedIn: " + e.getMessage(), "LINKEDIN_003");
        }
    }

    @Override
    public Resposta<Boolean> deletarPublicacao(String idPublicacao) {
        try {
            boolean ok = api.deletarPost(idPublicacao);
            return Resposta.sucesso(ok);
        } catch (Exception e) {
            return Resposta.erro("Erro deletar LinkedIn: " + e.getMessage(), "LINKEDIN_004");
        }
    }

    private String extrairTitulo(String texto) {
        if (texto == null)
            return "";
        return texto.length() <= 50 ? texto : texto.substring(0, 47) + "...";
    }

    @Override
    public String getNomePlataforma() {
        return "linkedin";
    }
}
