package br.ufc.russas.n2s.chronos.service;

import java.util.List;

import br.ufc.russas.n2s.chronos.beans.OrganizadorBeans;

public interface OrganizadorServiceIfc {
	
	void adicionaOrganizador(OrganizadorBeans organizador);
    
	void atualizaOrganizador(OrganizadorBeans organizador);
    
	void removeOrganizador(OrganizadorBeans organizador);
    
	List<OrganizadorBeans> listaTodosOrganizadores();
    
	OrganizadorBeans getOrganizador(long codOrganizador);
}
