package br.ufc.russas.n2s.chronos.controller;

import java.util.ArrayList;
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
import br.ufc.russas.n2s.chronos.beans.OrganizadorBeans;
import br.ufc.russas.n2s.chronos.beans.RealizacaoBeans;
import br.ufc.russas.n2s.chronos.beans.UsuarioBeans;
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
	public String getIndex(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UsuarioBeans usuario = (UsuarioBeans) session.getAttribute("usuarioChronos");
		List<AtividadeBeans> atividades = this.getAtividadeServiceIfc().listaTodasAtividadesHql();
		List<AtividadeBeans> novasatividades = new ArrayList<>();
		
        //Verifica se o participante está inscrito na atividade
        for (AtividadeBeans atividadebeans : atividades ) {
        	for(UsuarioBeans participante : atividadebeans.getParticipantes()) {
        		if (usuario.getCodUsuario()==participante.getCodUsuario()) {        				
        			novasatividades.add(atividadebeans);
        			break;        					
        		}        				
        	}
        	for(OrganizadorBeans organizador : atividadebeans.getOrganizadores()) {
        		if(usuario.getCodUsuario()==organizador.getUsuarioBeans().getCodUsuario()) {
        			novasatividades.add(atividadebeans);
        			break;
        		}
        	}
        }   		
        
		model.addAttribute("categoria", "Início");
		model.addAttribute("estado", "início");
		model.addAttribute("atividades", novasatividades);
		
		return "minhas-atividades";
	}
	
}