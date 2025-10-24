
package models;

public class Estatisticas {
    private final int curtidas;
    private final int compartilhamentos;
    private final int comentarios;
    private final int visualizacoes;

    public Estatisticas(int curtidas, int compartilhamentos, int comentarios, int visualizacoes) {
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
