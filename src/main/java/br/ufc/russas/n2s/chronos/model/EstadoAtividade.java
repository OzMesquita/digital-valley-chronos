package br.ufc.russas.n2s.chronos.model;

import javax.persistence.Embeddable;

/**
 *
 * @author Lav�nia Matoso ajustado por Gutenberg
 */

public interface EstadoAtividade {
    EnumEstadoAtividade execute(Atividade atividade);
}