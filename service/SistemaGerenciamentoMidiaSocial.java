
package service;

import interfaces.GerenciadorMidiaSocial;
import factory.SocialMediaFactory;
import models.Conteudo;
import models.Resposta;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SistemaGerenciamentoMidiaSocial {
    private final Map<String, GerenciadorMidiaSocial> gerenciadores = new ConcurrentHashMap<>();

    public SistemaGerenciamentoMidiaSocial() {
        carregarGerenciadores();
    }

    private void carregarGerenciadores() {
        for (GerenciadorMidiaSocial g : SocialMediaFactory.criarTodosGerenciadores()) {
            gerenciadores.put(g.getNomePlataforma().toLowerCase(), g);
        }
    }

    public Map<String, Resposta<?>> publicarEmTodasPlataformas(Conteudo conteudo) {
        Map<String, Resposta<?>> resultados = new ConcurrentHashMap<>();
        gerenciadores.forEach((nome, ger) -> {
            try {
                resultados.put(nome, ger.publicarConteudo(conteudo));
            } catch (Exception e) {
                resultados.put(nome, Resposta.erro("Erro genérico: " + e.getMessage(), "SISTEMA_500"));
            }
        });
        return resultados;
    }

    public Resposta<?> publicarEmPlataformaEspecifica(String plataforma, Conteudo conteudo) {
        GerenciadorMidiaSocial g = gerenciadores.get(plataforma.toLowerCase());
        if (g == null)
            return Resposta.erro("Plataforma não encontrada: " + plataforma, "SISTEMA_404");
        try {
            return g.publicarConteudo(conteudo);
        } catch (Exception e) {
            return Resposta.erro("Erro publicar: " + e.getMessage(), "SISTEMA_500");
        }
    }

    public Resposta<Boolean> agendarPublicacao(String plataforma, Conteudo conteudo, Date dataAgendamento) {
        GerenciadorMidiaSocial g = gerenciadores.get(plataforma.toLowerCase());
        if (g == null)
            return Resposta.erro("Plataforma não encontrada: " + plataforma, "SISTEMA_404");
        try {
            return g.agendarPublicacao(conteudo, dataAgendamento);
        } catch (Exception e) {
            return Resposta.erro("Erro agendar: " + e.getMessage(), "SISTEMA_500");
        }
    }

    public Resposta<Boolean> deletarPublicacao(String plataforma, String idPublicacao) {
        GerenciadorMidiaSocial g = gerenciadores.get(plataforma.toLowerCase());
        if (g == null)
            return Resposta.erro("Plataforma não encontrada: " + plataforma, "SISTEMA_404");
        try {
            return g.deletarPublicacao(idPublicacao);
        } catch (Exception e) {
            return Resposta.erro("Erro deletar: " + e.getMessage(), "SISTEMA_500");
        }
    }

    public Resposta<?> obterEstatisticas(String plataforma, String idPublicacao) {
        GerenciadorMidiaSocial g = gerenciadores.get(plataforma.toLowerCase());
        if (g == null)
            return Resposta.erro("Plataforma não encontrada: " + plataforma, "SISTEMA_404");
        try {
            return g.obterEstatisticas(idPublicacao);
        } catch (Exception e) {
            return Resposta.erro("Erro estatísticas: " + e.getMessage(), "SISTEMA_500");
        }
    }

    public List<String> getPlataformasDisponiveis() {
        return List.copyOf(gerenciadores.keySet());
    }

    public boolean isPlataformaDisponivel(String plataforma) {
        return gerenciadores.containsKey(plataforma.toLowerCase());
    }
}
