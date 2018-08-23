package br.ufc.russas.n2s.chronos.dao;

import br.ufc.russas.n2s.chronos.model.Responsavel;
import java.util.List;

public interface ResponsavelDAOIfc {
	void adicionaResponsavel(Responsavel responsavel);

	void atualizaResponsavel(Responsavel responsavel);

	void removeResponsavel(Responsavel responsavel);

	List<Responsavel> listaResponsavel(Responsavel responsavel);

	List<Responsavel> listaHqlResponsavel(String busca);

	Responsavel getResponsavel(Responsavel responsavel);
}
