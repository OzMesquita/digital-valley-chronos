package br.ufc.russas.n2s.chronos.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import br.ufc.russas.n2s.chronos.model.Apoio;

public class ApoioDAOImpl implements ApoioDAOIfc{

	private DAOIfc<Apoio> daoImpl;
	
	@Autowired
	public void setDAOIfc(@Qualifier("daoImpl")DAOIfc<Apoio> dao) {
		this.daoImpl = dao;
	}
	
	public ApoioDAOImpl(){}
	
	@Override
	public void adicionaApoio(Apoio apoio) {
		this.daoImpl.adiciona(apoio);		
	}

	@Override
	public void atualizaApoio(Apoio apoio) {
		this.daoImpl.atualiza(apoio);
	}

	@Override
	public void removeApoio(Apoio apoio) {
		this.daoImpl.remove(apoio);
	}
	
	@Override
	public List<Apoio> listaApoio(Apoio apoio) {
		return this.daoImpl.lista(apoio);
	}

	@Override
	public List<Apoio> listaHqlApoio(String busca) {
		return this.daoImpl.listaHqL(busca);
	}	

	@Override
	public Apoio getApoio(Apoio apoio) {
		return this.daoImpl.getObject(apoio, apoio.getCodApoio());
	}
}
