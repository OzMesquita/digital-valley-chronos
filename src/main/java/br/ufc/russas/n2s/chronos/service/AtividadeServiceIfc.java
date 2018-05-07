package br.ufc.russas.n2s.chronos.service;

import java.util.List;

import br.ufc.russas.n2s.chronos.beans.AtividadeBeans;
import br.ufc.russas.n2s.chronos.beans.UsuarioBeans;
import br.ufc.russas.n2s.chronos.model.Atividade;

public interface AtividadeServiceIfc extends ServiceIfc{
	
	AtividadeBeans adicionaAtividade(AtividadeBeans atividade) throws IllegalAccessException;
	
	AtividadeBeans atualizaAtividade(AtividadeBeans atividade) throws IllegalAccessException;
	
	void removeAtividade(AtividadeBeans atividade);
	
	List<AtividadeBeans> listaAtividades(Atividade atividade);
	
	AtividadeBeans getAtividade(long codAtividade);
}
