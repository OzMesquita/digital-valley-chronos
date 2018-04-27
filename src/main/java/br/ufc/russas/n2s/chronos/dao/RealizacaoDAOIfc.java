package br.ufc.russas.n2s.chronos.dao;

import java.util.List;

import br.ufc.russas.n2s.chronos.model.Realizacao;

public interface RealizacaoDAOIfc {

	void adicionaRealizacao(Realizacao realizacao);
	
	void atualizaRealizacao(Realizacao realizacao);
	
	void removeRealizacao(Realizacao realizacao);
	
	List<Realizacao> listaRealizacao(Realizacao realizacao);
	
	List<Realizacao> listaHqlRealizacao(String busca);
	
	Realizacao getRealizacao(Realizacao realizacao);
	
}
