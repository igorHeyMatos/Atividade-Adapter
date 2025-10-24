
package exceptions;

public class SocialMediaException extends RuntimeException {
    private final String codigo;

    public SocialMediaException(String message, String codigo) {
        super(message);
        this.codigo = codigo;
    }

    public String getCodigo() { return codigo; }
}
