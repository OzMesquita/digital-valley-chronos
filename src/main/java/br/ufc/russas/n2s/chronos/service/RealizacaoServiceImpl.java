package br.ufc.russas.n2s.chronos.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import br.ufc.russas.n2s.chronos.beans.RealizacaoBeans;
import br.ufc.russas.n2s.chronos.dao.RealizacaoDAOIfc;
import br.ufc.russas.n2s.chronos.model.Realizacao;

public class RealizacaoServiceImpl implements RealizacaoServiceIfc {
	private RealizacaoDAOIfc realizacaoDAOIfc;

	public RealizacaoDAOIfc getRealizacaoDAOIfc() {
		return realizacaoDAOIfc;
	}

	@Autowired(required = true)
	public void setRealizacaoDAOIfc(@Qualifier("realizacaoDAOIfc") RealizacaoDAOIfc realizacaoDAOIfc) {
		this.realizacaoDAOIfc = realizacaoDAOIfc;
	}

	@Override
	public void adicionaRealizacao(RealizacaoBeans realizacao) {
		this.getRealizacaoDAOIfc().adicionaRealizacao((Realizacao) realizacao.toBusiness());
	}

	@Override
	public void atualizaRealizacao(RealizacaoBeans realizacao) {
		this.getRealizacaoDAOIfc().atualizaRealizacao((Realizacao) realizacao.toBusiness());
	}

	@Override
	public void removeRealizacao(RealizacaoBeans realizacao) {
		this.getRealizacaoDAOIfc().removeRealizacao((Realizacao) realizacao.toBusiness());
	}

	@Override
	public List<RealizacaoBeans> listaTodasRealizacoes() {
		Realizacao r = new Realizacao();
		List<RealizacaoBeans> realizacoes = Collections.synchronizedList(new ArrayList<RealizacaoBeans>());
		List<Realizacao> result = this.getRealizacaoDAOIfc().listaRealizacao(r);
		for (Realizacao realizacao : result) {
			realizacoes.add((RealizacaoBeans) new RealizacaoBeans().toBeans(realizacao));
		}
		return realizacoes;
	}

	@Override
	public RealizacaoBeans getRealizacao(long codRealizacao) {
		Realizacao r = new Realizacao();
		r.setCodRealizacao(codRealizacao);
		return (RealizacaoBeans) new RealizacaoBeans().toBeans(this.getRealizacaoDAOIfc().getRealizacao(r));
	}
}