package br.ufc.russas.n2s.chronos.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import br.ufc.russas.n2s.chronos.model.Organizador;

public class OrganizadorDAOImpl implements OrganizadorIfc{

	private DAOIfc<Organizador> daoImpl;
	
	@Autowired
	public void setDAOIfc(@Qualifier("daoImpl")DAOIfc<Organizador> dao) {
		this.daoImpl = dao;
	}
	
	public OrganizadorDAOImpl() {}
	
	@Override
	public void adicionaOrganizador(Organizador organizador) {
		this.daoImpl.adiciona(organizador);
	}
	
	@Override
	public void atualizaOrganizador(Organizador organizador) {
		this.daoImpl.atualiza(organizador);
	}
	
	@Override
	public void removeOrganizador(Organizador organizador) {
		this.daoImpl.remove(organizador);
	}
	
	@Override
	public List<Organizador> listaOrganizador(Organizador organizador) {
		return this.daoImpl.lista(organizador);
	}

	@Override
	public List<Organizador> listaHqlOrganizador(String busca) {
		return this.daoImpl.listaHqL(busca);
	}
	
	@Override
	public Organizador getOrganizador(Organizador organizador) {
		return this.daoImpl.getObject(organizador, organizador.getCodOrganizador());
	}


}
