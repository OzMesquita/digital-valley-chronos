package br.ufc.russas.n2s.chronos.dao;

import java.util.List;

import br.ufc.russas.n2s.chronos.model.Colaborador;

public interface ColaboradorDAOIfc {
	void adicionaColaborador(Colaborador colaborador);

	void atualizaColaborador(Colaborador colaborador);

	void removeColaborador(Colaborador colaborador);

	List<Colaborador> listaColaborador(Colaborador colaborador);

	List<Colaborador> listaHqlColaborador(String busca);

	Colaborador getColaborador(Colaborador colaborador);

}
