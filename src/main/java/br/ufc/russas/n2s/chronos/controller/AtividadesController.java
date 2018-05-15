/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.chronos.controller;



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
import br.ufc.russas.n2s.chronos.service.AtividadeServiceIfc;

/**
 *
 * @author N2S
 */
@Controller("atividadeController")
@RequestMapping("/atividades")
public class AtividadesController { 

    
	private AtividadeServiceIfc atividadeServiceIfc;

	public AtividadeServiceIfc getAtividadeService() {
		return atividadeServiceIfc;
	}

	@Autowired(required=true)
	public void setAtividadeServiceIfc(@Qualifier("atividadeServiceIfc") AtividadeServiceIfc atividadeService) {
		this.atividadeServiceIfc = atividadeService;

	}
	
	@RequestMapping(value="/{codAtividade}", method = RequestMethod.GET)
	public String getAtividade(@PathVariable long codAtividade, Model model, HttpServletRequest request) {
		AtividadeBeans atividade = this.atividadeServiceIfc.getAtividade(codAtividade);
		HttpSession session = request.getSession();
		
		model.addAttribute("atividade", atividade);
		model.addAttribute("isResponsavel", true);
		session.setAttribute("atividade", atividade);
		return "atividade";
	}
           
}