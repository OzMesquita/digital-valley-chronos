package br.ufc.russas.n2s.chronos.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import br.ufc.russas.n2s.chronos.model.Colaborador;
import br.ufc.russas.n2s.chronos.model.Colaborador;

public class ColaboradorDAOImpl implements ColaboradorDAOIfc {
	private DAOIfc<Colaborador> daoImpl;
	
	@Autowired
	public void setDAOIfc(@Qualifier("daoImpl") DAOIfc<Colaborador> dao) {
		this.daoImpl = dao;
	}

	public ColaboradorDAOImpl() {
	}

	@Override
	public void adicionaColaborador(Colaborador colaborador) {
		this.daoImpl.adiciona(colaborador);
	}

	@Override
	public void atualizaColaborador(Colaborador colaborador) {
		this.daoImpl.atualiza(colaborador);
	}

	@Override
	public void removeColaborador(Colaborador colaborador) {
		this.daoImpl.remove(colaborador);
	}	
	
	@Override
	public List<Colaborador> listaColaborador(Colaborador colaborador) {
		return this.daoImpl.lista(colaborador);
	}

	@Override
	public List<Colaborador> listaHqlColaborador(String busca) {
		return this.daoImpl.listaHqL(busca);
	}

	@Override
	public Colaborador getColaborador(Colaborador colaborador) {
		// TODO Auto-generated method stub
		return this.daoImpl.getObject(colaborador, colaborador.getCodOrganizador());;
	}

	
}
