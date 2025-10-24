
package adapter;

import interfaces.GerenciadorMidiaSocial;
import models.*;
import api.TikTokAPI;

import java.util.Date;

public class TikTokAdapter implements GerenciadorMidiaSocial {
    private final TikTokAPI api;

    public TikTokAdapter(TikTokAPI api) {
        this.api = api;
    }

    @Override
    public Resposta<Publicacao> publicarConteudo(Conteudo conteudo) {
        try {
            String[] tags = conteudo.getHashtags() != null ? conteudo.getHashtags().toArray(new String[0])
                    : new String[0];
            String id = api.enviarVideo(conteudo.getTexto(), conteudo.getMidiaUrl(), tags);
            Publicacao p = new Publicacao(id, getNomePlataforma(), conteudo, new Date(), "PUBLICADO");
            return Resposta.sucesso(p);
        } catch (Exception e) {
            return Resposta.erro("Erro TikTok publicar: " + e.getMessage(), "TIKTOK_001");
        }
    }

    @Override
    public Resposta<Boolean> agendarPublicacao(Conteudo conteudo, Date dataAgendamento) {
        try {
            String[] tags = conteudo.getHashtags() != null ? conteudo.getHashtags().toArray(new String[0])
                    : new String[0];
            boolean ok = api.agendarVideo(conteudo.getTexto(), conteudo.getMidiaUrl(), tags, dataAgendamento);
            return Resposta.sucesso(ok);
        } catch (Exception e) {
            return Resposta.erro("Erro agendar TikTok: " + e.getMessage(), "TIKTOK_002");
        }
    }

    @Override
    public Resposta<Estatisticas> obterEstatisticas(String idPublicacao) {
        try {
            TikTokAPI.EstatisticasTikTok s = api.obterEstatisticasVideo(idPublicacao);
            Estatisticas e = new Estatisticas(s.getCurtidas(), s.getCompartilhamentos(), s.getComentarios(),
                    s.getVisualizacoes());
            return Resposta.sucesso(e);
        } catch (Exception e) {
            return Resposta.erro("Erro estatísticas TikTok: " + e.getMessage(), "TIKTOK_003");
        }
    }

    @Override
    public Resposta<Boolean> deletarPublicacao(String idPublicacao) {
        try {
            boolean ok = api.deletarVideo(idPublicacao);
            return Resposta.sucesso(ok);
        } catch (Exception e) {
            return Resposta.erro("Erro deletar TikTok: " + e.getMessage(), "TIKTOK_004");
        }
    }

    @Override
    public String getNomePlataforma() {
        return "tiktok";
    }
}
