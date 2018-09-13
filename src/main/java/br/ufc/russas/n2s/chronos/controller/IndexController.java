/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.chronos.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufc.russas.n2s.chronos.beans.AtividadeBeans;
import br.ufc.russas.n2s.chronos.beans.RealizacaoBeans;
import br.ufc.russas.n2s.chronos.model.Atividade;
import br.ufc.russas.n2s.chronos.model.EnumTipoAtividade;
import br.ufc.russas.n2s.chronos.service.AtividadeServiceIfc;

@Controller("indexController")
@RequestMapping("/")
public class IndexController {

	private AtividadeServiceIfc atividadeServiceIfc;

	public AtividadeServiceIfc getAtividadeServiceIfc() {
		return atividadeServiceIfc;
	}

	@Autowired(required = true)
	public void setAtividadeServiceIfc(@Qualifier("atividadeServiceIfc") AtividadeServiceIfc atividadeServiceIfc) {
		this.atividadeServiceIfc = atividadeServiceIfc;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getIndex(Model model) {
		Atividade atividade = new Atividade();
		atividade.setDivulgada(true);
		List<AtividadeBeans> atividades = this.getAtividadeServiceIfc().listaAtividadesOrfans(atividade);
		for (Iterator<AtividadeBeans> iterator = atividades.iterator(); iterator.hasNext();) {
			AtividadeBeans atividadeBeans = iterator.next();
			List<RealizacaoBeans> realizacoes = atividadeBeans.getRealizacao();
			for (Iterator<RealizacaoBeans> iterator2 = realizacoes.iterator(); iterator2.hasNext();) {
				RealizacaoBeans realizacaoBeans = iterator2.next();
				// mant�m a data mais recente na posi��o 0 para que possa ser exibida na pagina
				// inicio.jsp para cada atividade
				if (realizacaoBeans.getHoraInicio().isBefore(realizacoes.get(0).getHoraInicio()))
					realizacoes.set(0, realizacaoBeans);
			}
		}
		model.addAttribute("categoria", "In�cio");
		model.addAttribute("estado", "in�cio");
		model.addAttribute("atividades", atividades);
		return "inicio";
	}

	@RequestMapping(value = "/cadastrarSubAtividades", method = RequestMethod.GET)
	public String getCadastroSub(Model model, HttpServletRequest request) {
		return "cadastrar-atividade";
	}

	@RequestMapping(value = "/{categoria}", method = RequestMethod.GET)
	public String getCategoria(Model model, @PathVariable String categoria) {
		Atividade atividade = new Atividade();
		atividade.setTipoAtividade(EnumTipoAtividade.valorEnumPeloNome(categoria.toUpperCase()));
		List<AtividadeBeans> atividades = this.getAtividadeServiceIfc().listaAtividadesOrfans(atividade);
		model.addAttribute("categoria", "In�cio");
		model.addAttribute("estado", "in�cio");
		model.addAttribute("atividades", atividades);
		return "inicio";
	}
}
