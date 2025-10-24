
package api;

import java.util.Calendar;

public class LinkedinAPI {
    public static class EstatisticasLinkedin {
        private final int curtidas;
        private final int comentarios;
        private final int impressoes;

        public EstatisticasLinkedin(int curtidas, int comentarios, int impressoes) {
            this.curtidas = curtidas;
            this.comentarios = comentarios;
            this.impressoes = impressoes;
        }

        public int getCurtidas() {
            return curtidas;
        }

        public int getComentarios() {
            return comentarios;
        }

        public int getImpressoes() {
            return impressoes;
        }
    }

    public String compartilharArtigo(String titulo, String conteudo, String link) {
        return "linkedin_" + System.currentTimeMillis();
    }

    public boolean agendarPost(String titulo, String conteudo, String link, Calendar dataAgendamento) {
        return true;
    }

    public EstatisticasLinkedin obterAnalytics(String idPost) {
        return new EstatisticasLinkedin(30, 4, 300);
    }

    public boolean deletarPost(String idPost) {
        return true;
    }
}
