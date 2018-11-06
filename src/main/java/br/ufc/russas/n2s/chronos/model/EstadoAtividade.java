package br.ufc.russas.n2s.chronos.model;

import javax.persistence.Embeddable;

/**
 *
 * @author Lavínia Matoso ajustado por Gutenberg
 */

public interface EstadoAtividade {
    EnumEstadoAtividade execute(Atividade atividade);
}