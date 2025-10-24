package main;

import models.*;
import service.SistemaGerenciamentoMidiaSocial;
import factory.SocialMediaFactory;

import java.util.*;

public class ConsumidorSistemaMidiaSocial {
    public static void main(String[] args) {
        Map<String, String> conf = new HashMap<>();
        conf.put("twitter.habilitado", "true");
        conf.put("instagram.habilitado", "true");
        conf.put("linkedin.habilitado", "true");
        conf.put("tiktok.habilitado", "true");
        SocialMediaFactory.carregarConfiguracao(conf);

        SistemaGerenciamentoMidiaSocial sistema = new SistemaGerenciamentoMidiaSocial();

        Conteudo c = new Conteudo(
                "Texto de exemplo",
                "https://midia.exemplo/img.jpg",
                Arrays.asList("teste", "adapter"));

        System.out.println("Plataformas disponíveis: " + sistema.getPlataformasDisponiveis());

        Map<String, Resposta<?>> resultados = sistema.publicarEmTodasPlataformas(c);
        resultados.forEach((plataforma, resp) -> {
            if (resp.sucesso() && resp.getDados() instanceof Publicacao) {
                Publicacao p = (Publicacao) resp.getDados();
                System.out.println(plataforma + " > Publicado com ID: " + p.getId());
            } else {
                System.out.println(plataforma + " > Erro: " + resp.getMensagemErro());
            }
        });

        System.out.println("\nAgendando publicação no Instagram para daqui 10 segundos...");
        Calendar agendamento = Calendar.getInstance();
        agendamento.add(Calendar.SECOND, 10);
        Resposta<Boolean> agendamentoResp = sistema.agendarPublicacao("instagram", c, agendamento.getTime());
        System.out.println("Agendamento Instagram: " + (agendamentoResp.sucesso() ? "OK" : "Erro"));

        System.out.println("\nDeletando publicação do Twitter...");
        Resposta<?> pubResp = resultados.get("twitter");
        if (pubResp != null && pubResp.sucesso() && pubResp.getDados() instanceof Publicacao) {
            String id = ((Publicacao) pubResp.getDados()).getId();
            Resposta<Boolean> deleteResp = sistema.deletarPublicacao("twitter", id);
            System.out.println("Deletar Twitter: " + (deleteResp.sucesso() ? "OK" : "Falhou"));
        } else {
            System.out.println("Nenhuma publicação válida encontrada para deletar no Twitter.");
        }

        System.out.println("\nObtendo estatísticas do Instagram...");
        Resposta<?> pubInsta = resultados.get("instagram");
        if (pubInsta != null && pubInsta.sucesso() && pubInsta.getDados() instanceof Publicacao) {
            String id = ((Publicacao) pubInsta.getDados()).getId();
            Resposta<?> stats = sistema.obterEstatisticas("instagram", id);
            if (stats.sucesso() && stats.getDados() instanceof Estatisticas) {
                Estatisticas e = (Estatisticas) stats.getDados();
                System.out.println("Curtidas: " + e.getCurtidas() + ", Alcance: " + e.getVisualizacoes());
            }
        }
    }
}
