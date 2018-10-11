package br.ufc.russas.n2s.chronos.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import br.ufc.russas.n2s.chronos.beans.InscricaoAtividadeBeans;
import br.ufc.russas.n2s.chronos.dao.InscricaoDAOIfc;
import br.ufc.russas.n2s.chronos.model.InscricaoAtividade;

public class InscricaoServiceImpl implements InscricaoServiceIfc {
	private InscricaoDAOIfc inscricaoDAOIfc;

	public InscricaoServiceImpl() {
	}

	public InscricaoDAOIfc getInscricaoDAOIfc() {
		return inscricaoDAOIfc;
	}

	@Autowired(required = true)
	public void setApoioDAOIfc(@Qualifier("inscricaoDAOIfc") InscricaoDAOIfc inscricaoDAOIfc) {
		this.inscricaoDAOIfc = inscricaoDAOIfc;
	}
	@Override
	public void adicionaInscricao(InscricaoAtividadeBeans inscricao) {
		this.inscricaoDAOIfc.adicionaInscricao((InscricaoAtividade) inscricao.toBusiness());
	}

	@Override
	public void atualizaInscricao(InscricaoAtividadeBeans inscricao) {
		this.inscricaoDAOIfc.atualizaInscricao((InscricaoAtividade) inscricao.toBusiness());
	}

	@Override
	public void removeInscricao(InscricaoAtividadeBeans inscricao) {
		this.inscricaoDAOIfc.removeInscricao((InscricaoAtividade) inscricao.toBusiness());
	}

	@Override
	public List<InscricaoAtividadeBeans> listaTodasInscricões() {
		InscricaoAtividade inscricao = new InscricaoAtividade();
		List<InscricaoAtividadeBeans> inscricoes = Collections.synchronizedList(new ArrayList<InscricaoAtividadeBeans>());
		List<InscricaoAtividade> resultados = this.inscricaoDAOIfc.listaInscricao(inscricao);
		for (InscricaoAtividade p : resultados)
			inscricoes.add((InscricaoAtividadeBeans) new InscricaoAtividadeBeans().toBeans(p));
		return inscricoes;
	}

	@Override
	public InscricaoAtividadeBeans getInscricao(long codInscricao) {
		InscricaoAtividade inscricao = new InscricaoAtividade();
		inscricao.setCodInscricao(codInscricao);
		return (InscricaoAtividadeBeans) new InscricaoAtividadeBeans().toBeans(this.inscricaoDAOIfc.getInscricao(inscricao));
	}

}
