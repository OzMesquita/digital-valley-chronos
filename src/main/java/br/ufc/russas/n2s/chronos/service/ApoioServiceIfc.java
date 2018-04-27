package br.ufc.russas.n2s.chronos.service;

import java.util.List;

import br.ufc.russas.n2s.chronos.beans.ApoioBeans;

public interface ApoioServiceIfc {
	
	void adicionaApoio(ApoioBeans apoio);
    
	void atualizaApoio(ApoioBeans apoio);
    
	void removeApoio(ApoioBeans apoio);
    
	List<ApoioBeans> listaTodosApoios();
    
	ApoioBeans getApoio(long codApoio);
}
