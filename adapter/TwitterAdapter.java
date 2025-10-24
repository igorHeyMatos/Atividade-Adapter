
package adapter;

import interfaces.GerenciadorMidiaSocial;
import models.*;
import api.TwitterAPI;
import exceptions.SocialMediaException;

import java.util.Date;

public class TwitterAdapter implements GerenciadorMidiaSocial {
    private final TwitterAPI api;

    public TwitterAdapter(TwitterAPI api) {
        this.api = api;
    }

    @Override
    public Resposta<Publicacao> publicarConteudo(Conteudo conteudo) {
        try {
            String texto = formatarTexto(conteudo);
            String id = api.publicarTweet(texto, conteudo.getMidiaUrl());
            Publicacao p = new Publicacao(id, getNomePlataforma(), conteudo, new Date(), "PUBLICADO");
            return Resposta.sucesso(p);
        } catch (RuntimeException e) {
            throw new SocialMediaException("Twitter failed: " + e.getMessage(), "TWITTER_001");
        } catch (Exception e) {
            return Resposta.erro("Erro desconhecido Twitter: " + e.getMessage(), "TWITTER_999");
        }
    }

    @Override
    public Resposta<Boolean> agendarPublicacao(Conteudo conteudo, Date dataAgendamento) {
        try {
            String texto = formatarTexto(conteudo);
            boolean ok = api.agendarTweet(texto, conteudo.getMidiaUrl(), dataAgendamento.getTime());
            return Resposta.sucesso(ok);
        } catch (Exception e) {
            return Resposta.erro("Erro agendar Tweet: " + e.getMessage(), "TWITTER_002");
        }
    }

    @Override
    public Resposta<Estatisticas> obterEstatisticas(String idPublicacao) {
        try {
            TwitterAPI.EstatisticasTwitter s = api.obterEstatisticasTweet(idPublicacao);
            Estatisticas e = new Estatisticas(s.getCurtidas(), s.getRetweets(), s.getRespostas(), 0);
            return Resposta.sucesso(e);
        } catch (Exception e) {
            return Resposta.erro("Erro estatísticas Twitter: " + e.getMessage(), "TWITTER_003");
        }
    }

    @Override
    public Resposta<Boolean> deletarPublicacao(String idPublicacao) {
        try {
            boolean ok = api.deletarTweet(idPublicacao);
            return Resposta.sucesso(ok);
        } catch (Exception e) {
            return Resposta.erro("Erro deletar Tweet: " + e.getMessage(), "TWITTER_004");
        }
    }

    private String formatarTexto(Conteudo c) {
        StringBuilder s = new StringBuilder(c.getTexto() == null ? "" : c.getTexto());
        if (c.getHashtags() != null) {
            for (String h : c.getHashtags())
                s.append(" #").append(h);
        }
        return s.toString();
    }

    @Override
    public String getNomePlataforma() {
        return "twitter";
    }
}
