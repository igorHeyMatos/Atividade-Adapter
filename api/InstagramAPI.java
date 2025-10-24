
package api;

import java.util.Date;

public class InstagramAPI {
    public static class EstatisticasInstagram {
        private final int curtidas;
        private final int comentarios;
        private final int compartilhamentos;
        private final int alcance;

        public EstatisticasInstagram(int curtidas, int comentarios, int compartilhamentos, int alcance) {
            this.curtidas = curtidas;
            this.comentarios = comentarios;
            this.compartilhamentos = compartilhamentos;
            this.alcance = alcance;
        }

        public int getCurtidas() {
            return curtidas;
        }

        public int getComentarios() {
            return comentarios;
        }

        public int getCompartilhamentos() {
            return compartilhamentos;
        }

        public int getAlcance() {
            return alcance;
        }
    }

    public String publicarFoto(String legenda, String urlImagem, String[] tags) {
        if (legenda != null && legenda.length() > 2200)
            throw new RuntimeException("Legenda muito longa");
        return "instagram_" + System.currentTimeMillis();
    }

    public boolean agendarPost(String legenda, String urlImagem, String[] tags, Date dataAgendamento) {
        return true;
    }

    public EstatisticasInstagram obterInsights(String idPost) {
        return new EstatisticasInstagram(120, 10, 5, 1000);
    }

    public boolean removerPost(String idPost) {
        return true;
    }
}
