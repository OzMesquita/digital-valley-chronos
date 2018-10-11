package br.ufc.russas.n2s.chronos.dao;

import java.util.List;

import br.ufc.russas.n2s.chronos.model.InscricaoAtividade;

public interface InscricaoDAOIfc {
	void adicionaInscricao(InscricaoAtividade inscricao);

	void atualizaInscricao(InscricaoAtividade inscricao);

	void removeInscricao(InscricaoAtividade inscricao);

	List<InscricaoAtividade> listaInscricao(InscricaoAtividade inscricao);

	List<InscricaoAtividade> listaHqlInscricao(String busca);

	InscricaoAtividade getInscricao(InscricaoAtividade inscricao);
}
