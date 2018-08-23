package br.ufc.russas.n2s.chronos.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import br.ufc.russas.n2s.chronos.model.Atividade;

public class AtividadeDAOImpl implements AtividadeDAOIfc {
	private DAOIfc<Atividade> daoImpl;

	@Autowired
	public void setDAOIfc(@Qualifier("daoImpl") DAOIfc<Atividade> dao) {
		this.daoImpl = dao;
	}

	public AtividadeDAOImpl() {
	}

	@Override
	public Atividade adicionaAtividade(Atividade atividade) {
		Atividade a = this.daoImpl.adiciona(atividade);
		return a;
	}

	@Override
	public Atividade atualizaAtividade(Atividade atividade) {
		Atividade a = this.daoImpl.atualiza(atividade);
		return a;
	}

	@Override
	public void removeAtividade(Atividade atividade) {
		this.daoImpl.remove(atividade);
	}

	@Override
	public List<Atividade> listaAtividades(Atividade atividade) {
		return this.daoImpl.lista(atividade);
	}

	@Override
	public List<Atividade> listaHqlAtividade(String busca) {
		return this.daoImpl.listaHqL(busca);
	}

	@Override
	public Atividade getAtividade(Atividade atividade) {
		return this.daoImpl.getObject(atividade, atividade.getCodAtividade());
	}
}