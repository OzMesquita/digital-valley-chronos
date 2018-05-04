package br.ufc.russas.n2s.chronos.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import br.ufc.russas.n2s.chronos.model.Responsavel;

public class ResponsavelDAOImpl implements ResponsavelDAOIfc{

	private DAOIfc<Responsavel> daoImpl;
	
	@Autowired
	public void setDAOIfc(@Qualifier("daoImpl")DAOIfc<Responsavel> dao) {
		this.daoImpl = dao;
	}
	
	@Override
	public void adicionaResponsavel(Responsavel responsavel) {
		this.daoImpl.adiciona(responsavel);
	}

	@Override
	public void atualizaResponsavel(Responsavel responsavel) {
		this.daoImpl.atualiza(responsavel);
	}

	@Override
	public void removeResponsavel(Responsavel responsavel) {
		this.daoImpl.remove(responsavel);
	}

	@Override
	public List<Responsavel> listaResponsavel(Responsavel responsavel) {
		return this.daoImpl.lista(responsavel);
	}

	@Override
	public List<Responsavel> listaHqlResponsavel(String busca) {
		return this.daoImpl.listaHqL(busca);
	}
	
	@Override
	public Responsavel getResponsavel(Responsavel responsavel) {
		return this.daoImpl.getObject(responsavel, responsavel.getCodResponsavel());
	}
}
