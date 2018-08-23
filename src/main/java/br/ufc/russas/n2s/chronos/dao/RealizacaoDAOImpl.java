package br.ufc.russas.n2s.chronos.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import br.ufc.russas.n2s.chronos.model.Realizacao;

public class RealizacaoDAOImpl implements RealizacaoDAOIfc {
	private DAOIfc<Realizacao> daoImpl;

	@Autowired
	public void setDAOIfc(@Qualifier("daoImpl") DAOIfc<Realizacao> dao) {
		this.daoImpl = dao;
	}

	@Override
	public void adicionaRealizacao(Realizacao realizacao) {
		this.daoImpl.adiciona(realizacao);
	}

	@Override
	public void atualizaRealizacao(Realizacao realizacao) {
		this.daoImpl.atualiza(realizacao);
	}

	@Override
	public void removeRealizacao(Realizacao realizacao) {
		this.daoImpl.remove(realizacao);
	}

	@Override
	public List<Realizacao> listaRealizacao(Realizacao realizacao) {
		return this.daoImpl.lista(realizacao);
	}

	@Override
	public List<Realizacao> listaHqlRealizacao(String busca) {
		return this.daoImpl.listaHqL(busca);
	}

	@Override
	public Realizacao getRealizacao(Realizacao realizacao) {
		return this.daoImpl.getObject(realizacao, realizacao.getCodRealizacao());
	}
}