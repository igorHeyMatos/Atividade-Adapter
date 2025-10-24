
package factory;

import adapter.*;
import api.*;
import interfaces.GerenciadorMidiaSocial;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public final class SocialMediaFactory {
    private static final Map<String, String> configuracao = new ConcurrentHashMap<>();

    static {
        configuracao.put("twitter.habilitado", "true");
        configuracao.put("instagram.habilitado", "true");
        configuracao.put("linkedin.habilitado", "true");
        configuracao.put("tiktok.habilitado", "true");
    }

    private SocialMediaFactory() {
    }

    public static GerenciadorMidiaSocial criarGerenciador(String plataforma) {
        String key = plataforma.toLowerCase();
        if (!isPlataformaHabilitada(key))
            throw new IllegalArgumentException("Plataforma desabilitada: " + plataforma);

        switch (key) {
            case "twitter":
                return new TwitterAdapter(new TwitterAPI());
            case "instagram":
                return new InstagramAdapter(new InstagramAPI());
            case "linkedin":
                return new LinkedinAdapter(new LinkedinAPI());
            case "tiktok":
                return new TikTokAdapter(new TikTokAPI());
            default:
                throw new IllegalArgumentException("Plataforma não suportada: " + plataforma);
        }
    }

    public static List<GerenciadorMidiaSocial> criarTodosGerenciadores() {
        List<GerenciadorMidiaSocial> out = new ArrayList<>();
        for (String p : List.of("twitter", "instagram", "linkedin", "tiktok")) {
            if (isPlataformaHabilitada(p))
                out.add(criarGerenciador(p));
        }
        return out;
    }

    public static boolean isPlataformaHabilitada(String plataforma) {
        return Boolean.parseBoolean(configuracao.getOrDefault(plataforma.toLowerCase() + ".habilitado", "false"));
    }

    public static void carregarConfiguracao(Map<String, String> conf) {
        configuracao.putAll(conf);
    }

    public static void habilitarPlataforma(String plataforma, boolean habilitada) {
        configuracao.put(plataforma.toLowerCase() + ".habilitado", String.valueOf(habilitada));
    }
}
