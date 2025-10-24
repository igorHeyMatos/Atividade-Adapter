
package interfaces;

import models.Conteudo;
import models.Resposta;

import java.util.Date;

public interface GerenciadorMidiaSocial {
    Resposta<?> publicarConteudo(Conteudo conteudo);

    Resposta<Boolean> agendarPublicacao(Conteudo conteudo, Date dataAgendamento);

    Resposta<?> obterEstatisticas(String idPublicacao);

    Resposta<Boolean> deletarPublicacao(String idPublicacao);

    String getNomePlataforma();
}
