package br.ufc.russas.n2s.chronos.dao;

import java.util.List;
import br.ufc.russas.n2s.chronos.model.Apoio;

public interface ApoioDAOIfc {
	
	void adicionaApoio(Apoio apoio);
	
	void atualizaApoio(Apoio apoio);
	
	void removeApoio(Apoio apoio);
	
	List<Apoio> listaApoio(Apoio apoio);
	
	List<Apoio> listaHqlApoio(String busca);
	
	Apoio getApoio(Apoio apoio);

}
