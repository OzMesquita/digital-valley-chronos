package br.ufc.russas.n2s.chronos.service;
import java.util.List;
import br.ufc.russas.n2s.chronos.beans.ResponsavelBeans;
public interface ResponsavelServiceIfc {
	void adicionaResponsavel(ResponsavelBeans responsavel);
	void atualizaResponsavel(ResponsavelBeans responsavel);
	void removeResponsavel(ResponsavelBeans responsavel);
	List<ResponsavelBeans> listaTodosResponsavels();
	ResponsavelBeans getResponsavel(long codResponsavel);
}