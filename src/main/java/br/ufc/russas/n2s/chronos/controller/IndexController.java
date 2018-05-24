/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.chronos.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufc.russas.n2s.chronos.beans.AtividadeBeans;
import br.ufc.russas.n2s.chronos.model.Atividade;
import br.ufc.russas.n2s.chronos.model.EnumTipoAtividade;
import br.ufc.russas.n2s.chronos.service.AtividadeServiceIfc;

/**
 *
 * @author N2S
 */
@Controller("indexController")
@RequestMapping("/")
public class IndexController { 

	private AtividadeServiceIfc atividadeServiceIfc;
    
    public AtividadeServiceIfc getAtividadeServiceIfc(){
        return atividadeServiceIfc;
    }
    
    @Autowired(required = true)
    public void setAtividadeServiceIfc(@Qualifier("atividadeServiceIfc")AtividadeServiceIfc atividadeServiceIfc){
        this.atividadeServiceIfc = atividadeServiceIfc;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String getIndex(Model model) {
    	Atividade atividade = new Atividade();	
		List<AtividadeBeans> atividades = this.getAtividadeServiceIfc().listaAtividades(atividade);
	    model.addAttribute("categoria", "In�cio");
	    model.addAttribute("estado", "in�cio");
	    model.addAttribute("atividades", atividades);        
        return "inicio";
    }
    
    @RequestMapping(value="/cadastrarAtividades", method = RequestMethod.GET)
    public String getCadastro(Model model) {
        return "cadastrar-atividade";
    }
    
    @RequestMapping(value="/{categoria}", method = RequestMethod.GET)
    public String getCategoria(Model model, @PathVariable String categoria) {
        Atividade atividade = new Atividade();
        //atividade.setTipoAtividade(EnumTipoAtividade.valueOf((categoria.replace("_", " ")).toUpperCase()));
        
        List<AtividadeBeans> atividades = this.getAtividadeServiceIfc().listaAtividades(atividade);
        model.addAttribute("categoria", "In�cio");
        model.addAttribute("estado", "in�cio");
        model.addAttribute("atividades", atividades); 
        return "inicio";
    }
           
}