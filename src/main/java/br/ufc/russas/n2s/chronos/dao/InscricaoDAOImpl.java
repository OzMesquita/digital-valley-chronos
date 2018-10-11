package br.ufc.russas.n2s.chronos.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufc.russas.n2s.chronos.model.InscricaoAtividade;

@Service("isncricaoDAOIfc")
@Transactional
public class InscricaoDAOImpl implements InscricaoDAOIfc {
	private DAOIfc<InscricaoAtividade> daoImpl;
	
	@Autowired
	public void setDAOIfc(@Qualifier("daoImpl") DAOIfc<InscricaoAtividade> dao) {
		this.daoImpl = dao;
	}
	
	public InscricaoDAOImpl () {
	}
	
	@Override
	public void adicionaInscricao(InscricaoAtividade inscricao) {
		this.daoImpl.adiciona(inscricao);
	}

	@Override
	public void atualizaInscricao(InscricaoAtividade inscricao) {
		this.daoImpl.atualiza(inscricao);
	}

	@Override
	public void removeInscricao(InscricaoAtividade inscricao) {
		this.daoImpl.remove(inscricao);
	}

	@Override
	public List<InscricaoAtividade> listaInscricao(InscricaoAtividade inscricao) {
		return this.daoImpl.lista(inscricao);
	}

	@Override
	public List<InscricaoAtividade> listaHqlInscricao(String busca) {
		return this.daoImpl.listaHqL(busca);
	}

	@Override
	@Transactional
	public InscricaoAtividade getInscricao(InscricaoAtividade inscricao) {
		return this.daoImpl.getObject(inscricao, inscricao.getCodInscricao());
	}

}
