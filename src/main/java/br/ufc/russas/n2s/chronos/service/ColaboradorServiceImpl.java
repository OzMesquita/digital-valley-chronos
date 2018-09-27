package br.ufc.russas.n2s.chronos.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import br.ufc.russas.n2s.chronos.beans.ColaboradorBeans;
import br.ufc.russas.n2s.chronos.dao.ColaboradorDAOIfc;
import br.ufc.russas.n2s.chronos.model.Colaborador;

public class ColaboradorServiceImpl implements ColaboradorServiceIfc {
	private ColaboradorDAOIfc colaboradorDAOIfc;

	public ColaboradorServiceImpl() {
	}

	public ColaboradorDAOIfc getColaboradorDAOIfc() {
		return colaboradorDAOIfc;
	}

	@Autowired(required = true)
	public void setColaboradorDAOIfc(@Qualifier("colaboradorDAOIfc") ColaboradorDAOIfc colaboradorDAOIfc) {
		this.colaboradorDAOIfc = colaboradorDAOIfc;
	}

	@Override
	public void adicionaColaborador(ColaboradorBeans colaborador) {
		this.colaboradorDAOIfc.adicionaColaborador((Colaborador) colaborador.toBusiness());
	}

	@Override
	public void atualizaColaborador(ColaboradorBeans colaborador) {
		this.colaboradorDAOIfc.atualizaColaborador((Colaborador) colaborador.toBusiness());
	}

	@Override
	public void removeColaborador(ColaboradorBeans colaborador) {
		this.colaboradorDAOIfc.removeColaborador((Colaborador) colaborador.toBusiness());
	}

	@Override
	public List<ColaboradorBeans> listaTodosColaboradores() {
		Colaborador colaborador = new Colaborador();
		List<ColaboradorBeans> colaboradores = Collections.synchronizedList(new ArrayList<ColaboradorBeans>());
		List<Colaborador> resultados = this.colaboradorDAOIfc.listaColaborador(colaborador);
		for (Colaborador p : resultados)
			colaboradores.add((ColaboradorBeans) new ColaboradorBeans().toBeans(p));
		return colaboradores;
	}

	@Override
	public ColaboradorBeans getColaborador(long CodColaborador) {
		Colaborador colaborador = new Colaborador();
		colaborador.setCodColaborador(CodColaborador);
		return (ColaboradorBeans) new ColaboradorBeans().toBeans(this.colaboradorDAOIfc.getColaborador(colaborador));
	}

}
