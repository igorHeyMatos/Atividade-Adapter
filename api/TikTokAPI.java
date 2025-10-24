
package api;

import java.util.Date;

public class TikTokAPI {
    public static class EstatisticasTikTok {
        private final int curtidas;
        private final int compartilhamentos;
        private final int comentarios;
        private final int visualizacoes;

        public EstatisticasTikTok(int curtidas, int compartilhamentos, int comentarios, int visualizacoes) {
            this.curtidas = curtidas;
            this.compartilhamentos = compartilhamentos;
            this.comentarios = comentarios;
            this.visualizacoes = visualizacoes;
        }

        public int getCurtidas() {
            return curtidas;
        }

        public int getCompartilhamentos() {
            return compartilhamentos;
        }

        public int getComentarios() {
            return comentarios;
        }

        public int getVisualizacoes() {
            return visualizacoes;
        }
    }

    public String enviarVideo(String descricao, String urlVideo, String[] hashtags) {
        if (descricao != null && descricao.length() > 150)
            throw new RuntimeException("Descrição muito longa para TikTok");
        return "tiktok_" + System.currentTimeMillis();
    }

    public boolean agendarVideo(String descricao, String urlVideo, String[] hashtags, Date dataAgendamento) {
        return true;
    }

    public EstatisticasTikTok obterEstatisticasVideo(String idVideo) {
        return new EstatisticasTikTok(300, 50, 10, 2000);
    }

    public boolean deletarVideo(String idVideo) {
        return true;
    }
}
