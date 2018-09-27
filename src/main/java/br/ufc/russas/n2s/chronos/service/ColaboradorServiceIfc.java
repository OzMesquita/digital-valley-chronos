package br.ufc.russas.n2s.chronos.service;

import java.util.List;
import br.ufc.russas.n2s.chronos.beans.ColaboradorBeans;

public interface ColaboradorServiceIfc {
	void adicionaColaborador(ColaboradorBeans colaborador);
	
	void atualizaColaborador(ColaboradorBeans colaborador);

	void removeColaborador(ColaboradorBeans colaborador);

	List<ColaboradorBeans> listaTodosColaboradores();

	ColaboradorBeans getColaborador(long CodColaborador);
}
