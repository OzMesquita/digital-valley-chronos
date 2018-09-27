package br.ufc.russas.n2s.chronos.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufc.russas.n2s.chronos.beans.AtividadeBeans;
import br.ufc.russas.n2s.chronos.beans.ColaboradorBeans;
import br.ufc.russas.n2s.chronos.beans.ColaboradorBeans;
import br.ufc.russas.n2s.chronos.service.AtividadeServiceIfc;

@Controller("colaboradorController")
@RequestMapping("/colaboradores")
public class ColaboradorController {

	private AtividadeServiceIfc atividadeServiceIfc;

	public AtividadeServiceIfc getAtividadeServiceIfc() {
		return atividadeServiceIfc;
	}

	@Autowired(required = true)
	public void setAtividadeServiceIfc(@Qualifier("atividadeServiceIfc") AtividadeServiceIfc atividadeServiceIfc) {
		this.atividadeServiceIfc = atividadeServiceIfc;
	}

	@RequestMapping(value = "/{codAtividade}", method = RequestMethod.GET)
	public String getColaboradores(@PathVariable long codAtividade, Model model) {
		AtividadeBeans atividadeBeans = this.atividadeServiceIfc.getAtividade(codAtividade);
		List<ColaboradorBeans> colaborador = Collections.synchronizedList(new ArrayList<ColaboradorBeans>());
		colaborador = atividadeBeans.getColaboradores();
		model.addAttribute("colaborador", colaborador);
		return "colaborador";
	}
}
