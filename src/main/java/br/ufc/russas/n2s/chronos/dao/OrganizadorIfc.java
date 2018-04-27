package br.ufc.russas.n2s.chronos.dao;

import java.util.List;

import br.ufc.russas.n2s.chronos.model.Organizador;

public interface OrganizadorIfc {
	
	void adicionaOrganizador(Organizador organizador);
	
	void atualizaOrganizador(Organizador organizador);
	
	void removeOrganizador(Organizador organizador);
	
	List<Organizador> listaOrganizador(Organizador organizador);
	
	List<Organizador> listaHqlOrganizador(String busca);
	
	Organizador getOrganizador(Organizador organizador);

}
