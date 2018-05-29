package br.ufc.russas.n2s.chronos.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufc.russas.n2s.chronos.beans.AtividadeBeans;
import br.ufc.russas.n2s.chronos.beans.UsuarioBeans;
import br.ufc.russas.n2s.chronos.dao.AtividadeDAOIfc;
import br.ufc.russas.n2s.chronos.model.Atividade;
import br.ufc.russas.n2s.chronos.model.AtividadeProxy;
import br.ufc.russas.n2s.chronos.model.UsuarioChronos;

@Service("atividadeServiceIfc")
@Transactional
public class AtividadeServiceImpl implements AtividadeServiceIfc {

	private AtividadeDAOIfc atividadeDAOIfc;
	private UsuarioBeans usuario;

	public AtividadeServiceImpl() {
	}

	public AtividadeDAOIfc getAtividadeDAOIfc() {
		return atividadeDAOIfc;
	}

	@Autowired(required = true)
	public void setAtividadeDAOIfc(@Qualifier("atividadeDAOIfc")AtividadeDAOIfc atividadeDAOIfc){
		this.atividadeDAOIfc = atividadeDAOIfc;
	}

	@Override
	public void setUsuario(UsuarioBeans usuario) {
		this.usuario = usuario;
	}

	@Override
	public AtividadeBeans adicionaAtividade(AtividadeBeans atividade) throws IllegalAccessException {
		UsuarioChronos usuario = (UsuarioChronos) this.usuario.toBusiness();
		AtividadeProxy ap = new AtividadeProxy(usuario);
		Atividade a = getAtividadeDAOIfc().adicionaAtividade(ap.adicionaAtividade((Atividade) atividade.toBusiness()));
		return (AtividadeBeans) atividade.toBeans(a);
	}

	@Override
	public AtividadeBeans atualizaAtividade(AtividadeBeans atividade) throws IllegalAccessException {
		UsuarioChronos usuario = (UsuarioChronos) this.usuario.toBusiness();
		AtividadeProxy ap = new AtividadeProxy(usuario);
		Atividade a = getAtividadeDAOIfc().atualizaAtividade(ap.atualizaAtividade((Atividade) atividade.toBusiness()));
		return (AtividadeBeans) new AtividadeBeans().toBeans(a);
	}

	@Override
	public void removeAtividade(AtividadeBeans atividade) {
		this.getAtividadeDAOIfc().removeAtividade((Atividade) atividade.toBusiness());
	}

	@Override
	@Transactional
	public List<AtividadeBeans> listaAtividades(Atividade atividade) {
		List<AtividadeBeans> atividades = Collections.synchronizedList(new ArrayList<AtividadeBeans>());
		List<Atividade> resultado = this.getAtividadeDAOIfc().listaAtividades(atividade);
//		System.out.println(resultado.size());
		for(Atividade a : resultado) {
			atividades.add((AtividadeBeans) new AtividadeBeans().toBeans(a));
		}
		return atividades;				
	}
	
	@Override
	@Transactional
	public List<AtividadeBeans> listaAtividadesHql(String string) {
		List<AtividadeBeans> atividades = Collections.synchronizedList(new ArrayList<AtividadeBeans>());
		List<Atividade> resultado = this.getAtividadeDAOIfc().listaHqlAtividade("from Atividade a where a.pai="+string);
		for(Atividade a : resultado) {
			atividades.add((AtividadeBeans) new AtividadeBeans().toBeans(a));
		}
		return atividades;				
	}

	@Override
	@Transactional
	public AtividadeBeans getAtividade(long codAtividade) {
		Atividade atividade = new Atividade();
		atividade.setCodAtividade(codAtividade);
		AtividadeBeans retorno = (AtividadeBeans) new AtividadeBeans().toBeans(this.getAtividadeDAOIfc().getAtividade(atividade));
		retorno.setSubAtividade(this.listaAtividadesHql(codAtividade+""));
		return retorno;
	}



}
