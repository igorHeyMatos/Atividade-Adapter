
package api;


public class TwitterAPI {
    public static class EstatisticasTwitter {
        private final int curtidas;
        private final int retweets;
        private final int respostas;

        public EstatisticasTwitter(int curtidas, int retweets, int respostas) {
            this.curtidas = curtidas;
            this.retweets = retweets;
            this.respostas = respostas;
        }

        public int getCurtidas() {
            return curtidas;
        }

        public int getRetweets() {
            return retweets;
        }

        public int getRespostas() {
            return respostas;
        }
    }

    public String publicarTweet(String mensagem, String urlMidia) {
        if (mensagem != null && mensagem.length() > 280)
            throw new RuntimeException("Tweet muito longo");
        return "twitter_" + System.currentTimeMillis();
    }

    public boolean agendarTweet(String mensagem, String urlMidia, long timestamp) {
        return true;
    }

    public EstatisticasTwitter obterEstatisticasTweet(String idTweet) {
        return new EstatisticasTwitter(50, 5, 2);
    }

    public boolean deletarTweet(String idTweet) {
        return true;
    }
}
