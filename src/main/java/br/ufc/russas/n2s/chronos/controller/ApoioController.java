package br.ufc.russas.n2s.chronos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufc.russas.n2s.chronos.beans.AtividadeBeans;
import br.ufc.russas.n2s.chronos.service.AtividadeServiceIfc;

@Controller("apoioController")
@RequestMapping("/apoiadores")
public class ApoioController {

	private AtividadeServiceIfc atividadeServiceIfc;
	
	 public AtividadeServiceIfc getAtividadeServiceIfc(){
       return atividadeServiceIfc;
	 }
   
	 @Autowired(required = true)
	    public void setAtividadeServiceIfc(@Qualifier("atividadeServiceIfc")AtividadeServiceIfc atividadeServiceIfc){
	        this.atividadeServiceIfc = atividadeServiceIfc;
	 }
	
   @RequestMapping(value="/{codAtividade}", method = RequestMethod.GET)
   public String getApoiadores(@PathVariable long codAtividade, Model model){
	   AtividadeBeans atividade = this.atividadeServiceIfc.getAtividade(codAtividade);
   	
   	
   	model.addAttribute("atividade", atividade);
   	return "apoiadores";
   }
   
}
