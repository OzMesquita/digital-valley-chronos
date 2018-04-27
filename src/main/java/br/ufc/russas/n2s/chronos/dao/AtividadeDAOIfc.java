package br.ufc.russas.n2s.chronos.dao;

import java.util.List;

import br.ufc.russas.n2s.chronos.model.Atividade;
import br.ufc.russas.n2s.chronos.model.Responsavel;

public interface AtividadeDAOIfc {

	void adicionaAtividade(Atividade atividade);
	
	void atualizaAtividade(Atividade atividade);
	
	void removeAtividade(Atividade atividade);
	
	List<Atividade> listaAtividades(Atividade atividade);
	
	List<Atividade> listaHqlAtividade(String busca);
	
	Atividade getAtividade(Atividade atividade);
	
}
