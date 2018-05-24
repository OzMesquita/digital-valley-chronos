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

import br.ufc.russas.n2s.chronos.beans.RealizacaoBeans;
import br.ufc.russas.n2s.chronos.service.RealizacaoServiceIfc;

@Controller("realizacaoController")
@RequestMapping("/realizacao")
public class RealizacaoController {

	private RealizacaoServiceIfc realizacaoServiceIfc;
	
	public RealizacaoServiceIfc getRealizacaoServiceIfc(){
        return realizacaoServiceIfc;
    }
    
    @Autowired(required = true)
    public void setRealizacaoServiceIfc(@Qualifier("realizacaoServiceIfc")RealizacaoServiceIfc realizacaoServiceIfc){
        this.realizacaoServiceIfc = realizacaoServiceIfc;
    }
	
    @RequestMapping(value="/{codRealizacao}", method = RequestMethod.GET)
    public String getRealizacoes(@PathVariable long codRealizacao, Model model){
    	RealizacaoBeans realizacao = this.realizacaoServiceIfc.getRealizacao(codRealizacao);
    	model.addAttribute("realizacao", realizacao);
    	
    	return "realizacao";
    }
	
}
