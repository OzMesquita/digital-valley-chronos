package br.ufc.russas.n2s.chronos.service;

import br.ufc.russas.n2s.chronos.beans.RealizacaoBeans;
import java.util.List;

public interface RealizacaoServiceIfc {
	void adicionaRealizacao(RealizacaoBeans realizacao);

	void atualizaRealizacao(RealizacaoBeans realizacao);

	void removeRealizacao(RealizacaoBeans realizacao);

	List<RealizacaoBeans> listaTodasRealizacoes();

	RealizacaoBeans getRealizacao(long codRealizacao);
}