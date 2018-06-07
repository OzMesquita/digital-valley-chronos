package br.ufc.russas.n2s.chronos.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufc.russas.n2s.chronos.beans.AtividadeBeans;
import br.ufc.russas.n2s.chronos.model.Atividade;
import br.ufc.russas.n2s.chronos.service.AtividadeServiceIfc;

@Controller("subatividadeController")
@RequestMapping("/subatividades")
public class SubAtividadeController {

	private AtividadeServiceIfc atividadeServiceIfc;
	
	 public AtividadeServiceIfc getAtividadeServiceIfc(){
        return atividadeServiceIfc;
    }
    
    @Autowired(required = true)
    public void setAtividadeServiceIfc(@Qualifier("atividadeServiceIfc")AtividadeServiceIfc atividadeServiceIfc){
        this.atividadeServiceIfc = atividadeServiceIfc;
    }
	
    @RequestMapping(value="/{codAtividade}", method = RequestMethod.GET)
    public String getSubatividades(@PathVariable long codAtividade, Model model){
    	AtividadeBeans atividade = this.atividadeServiceIfc.getAtividade(codAtividade);
  
    	
    	
    	model.addAttribute("atividade", atividade);
    	return "subatividade";
    }
    
	@RequestMapping(value="/cadastra/{codAtividade}",method = RequestMethod.GET)
	public String adiciona(@PathVariable long codAtividade, Model model, HttpServletRequest request) throws IOException,IllegalAccessException{
		
		AtividadeBeans atividade = this.getAtividadeServiceIfc().getAtividade(codAtividade);
		model.addAttribute("pai", atividade);
		request.getSession().setAttribute("pai", atividade);
		return ("redirect:/cadastrarSubAtividades/");
	}
	
}
