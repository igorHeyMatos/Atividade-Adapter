
package models;

public class Resposta<T> {
    private final boolean sucesso;
    private final T dados;
    private final String mensagemErro;
    private final String codigoErro;

    private Resposta(boolean sucesso, T dados, String mensagemErro, String codigoErro) {
        this.sucesso = sucesso;
        this.dados = dados;
        this.mensagemErro = mensagemErro;
        this.codigoErro = codigoErro;
    }

    public static <T> Resposta<T> sucesso(T dados) {
        return new Resposta<>(true, dados, null, null);
    }

    public static <T> Resposta<T> erro(String mensagemErro, String codigoErro) {
        return new Resposta<>(false, null, mensagemErro, codigoErro);
    }

    public boolean sucesso() {
        return sucesso;
    }

    public T getDados() {
        return dados;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public String getCodigoErro() {
        return codigoErro;
    }
}
