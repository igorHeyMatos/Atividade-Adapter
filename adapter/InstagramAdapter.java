
package adapter;

import interfaces.GerenciadorMidiaSocial;
import models.*;
import api.InstagramAPI;
import exceptions.SocialMediaException;

import java.util.Date;

public class InstagramAdapter implements GerenciadorMidiaSocial {
    private final InstagramAPI api;

    public InstagramAdapter(InstagramAPI api) {
        this.api = api;
    }

    @Override
    public Resposta<Publicacao> publicarConteudo(Conteudo conteudo) {
        try {
            String[] tags = conteudo.getHashtags() != null ? conteudo.getHashtags().toArray(new String[0])
                    : new String[0];
            String id = api.publicarFoto(conteudo.getTexto(), conteudo.getMidiaUrl(), tags);
            Publicacao p = new Publicacao(id, getNomePlataforma(), conteudo, new Date(), "PUBLICADO");
            return Resposta.sucesso(p);
        } catch (RuntimeException e) {
            throw new SocialMediaException("Instagram failed: " + e.getMessage(), "INSTAGRAM_001");
        } catch (Exception e) {
            return Resposta.erro("Erro desconhecido no Instagram: " + e.getMessage(), "INSTAGRAM_999");
        }
    }

    @Override
    public Resposta<Boolean> agendarPublicacao(Conteudo conteudo, Date dataAgendamento) {
        try {
            String[] tags = conteudo.getHashtags() != null ? conteudo.getHashtags().toArray(new String[0])
                    : new String[0];
            boolean ok = api.agendarPost(conteudo.getTexto(), conteudo.getMidiaUrl(), tags, dataAgendamento);
            return Resposta.sucesso(ok);
        } catch (RuntimeException e) {
            return Resposta.erro("Erro agendar Instagram: " + e.getMessage(), "INSTAGRAM_002");
        }
    }

    @Override
    public Resposta<Estatisticas> obterEstatisticas(String idPublicacao) {
        try {
            InstagramAPI.EstatisticasInstagram s = api.obterInsights(idPublicacao);
            Estatisticas e = new Estatisticas(s.getCurtidas(), s.getCompartilhamentos(), s.getComentarios(),
                    s.getAlcance());
            return Resposta.sucesso(e);
        } catch (Exception e) {
            return Resposta.erro("Erro obter insights Instagram: " + e.getMessage(), "INSTAGRAM_003");
        }
    }

    @Override
    public Resposta<Boolean> deletarPublicacao(String idPublicacao) {
        try {
            boolean ok = api.removerPost(idPublicacao);
            return Resposta.sucesso(ok);
        } catch (Exception e) {
            return Resposta.erro("Erro deletar Instagram: " + e.getMessage(), "INSTAGRAM_004");
        }
    }

    @Override
    public String getNomePlataforma() {
        return "instagram";
    }
}
