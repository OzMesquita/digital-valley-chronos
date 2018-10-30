package br.ufc.russas.n2s.chronos.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import br.ufc.russas.n2s.chronos.service.AtividadeServiceIfc;

@Controller("atividadeController")
@RequestMapping("/atividades")
public class AtividadesController {

	private AtividadeServiceIfc atividadeServiceIfc;

	public AtividadeServiceIfc getAtividadeService() {
		return atividadeServiceIfc;
	}
	
	public AtividadeServiceIfc getAtividadeServiceIfc() {
		return atividadeServiceIfc;
	}

	@Autowired(required = true)
	public void setAtividadeServiceIfc(@Qualifier("atividadeServiceIfc") AtividadeServiceIfc atividadeService) {
		this.atividadeServiceIfc = atividadeService;

	}

	@RequestMapping(value = "/{codAtividade}", method = RequestMethod.GET)
	public String getAtividade(@PathVariable long codAtividade, Model model, HttpServletRequest request) {
		AtividadeBeans atividade = this.atividadeServiceIfc.getAtividade(codAtividade);
		HttpSession session = request.getSession();
		model.addAttribute("atividade", atividade);
		model.addAttribute("isResponsavel", true);
		session.setAttribute("atividade", atividade);
		return "atividade";
	}
	
	@RequestMapping(value = "/minhas-atividades", method = RequestMethod.GET)	
	public String getIndex(Model model) {
		Atividade atividade = new Atividade();
		atividade.setDivulgada(true);
		List<AtividadeBeans> atividades = this.getAtividadeServiceIfc().listaAtividadesOrfans(atividade);
		for (Iterator<AtividadeBeans> iterator = atividades.iterator(); iterator.hasNext();) {
			AtividadeBeans atividadeBeans = iterator.next();
			List<RealizacaoBeans> realizacoes = atividadeBeans.getRealizacao();
			for (Iterator<RealizacaoBeans> iterator2 = realizacoes.iterator(); iterator2.hasNext();) {
				RealizacaoBeans realizacaoBeans = iterator2.next();
				// mantém a data mais recente na posição 0 para que possa ser exibida na pagina
				// inicio.jsp para cada atividade
				if (realizacaoBeans.getHoraInicio().isBefore(realizacoes.get(0).getHoraInicio()))
					realizacoes.set(0, realizacaoBeans);
			}
		}
		model.addAttribute("categoria", "Início");
		model.addAttribute("estado", "início");
		model.addAttribute("atividades", atividades);
		return "minhas-atividades";
	}
}