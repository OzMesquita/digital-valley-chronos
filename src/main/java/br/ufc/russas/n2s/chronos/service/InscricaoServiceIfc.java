package br.ufc.russas.n2s.chronos.service;

import java.util.List;

import br.ufc.russas.n2s.chronos.beans.InscricaoAtividadeBeans;

public interface InscricaoServiceIfc {
	void adicionaInscricao(InscricaoAtividadeBeans inscricao);

	void atualizaInscricao(InscricaoAtividadeBeans inscricao);

	void removeInscricao(InscricaoAtividadeBeans inscricao);

	List<InscricaoAtividadeBeans> listaTodasInscric�es();

	InscricaoAtividadeBeans getInscricao(long codInscricao);
}
