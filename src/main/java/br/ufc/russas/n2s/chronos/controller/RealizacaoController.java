package br.ufc.russas.n2s.chronos.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufc.russas.n2s.chronos.service.AtividadeServiceIfc;
import br.ufc.russas.n2s.chronos.beans.AtividadeBeans;
import br.ufc.russas.n2s.chronos.beans.OrganizadorBeans;
import br.ufc.russas.n2s.chronos.beans.RealizacaoBeans;
import br.ufc.russas.n2s.chronos.beans.UsuarioBeans;
import br.ufc.russas.n2s.chronos.model.Atividade;
import br.ufc.russas.n2s.chronos.model.EnumPermissao;
import br.ufc.russas.n2s.chronos.service.RealizacaoServiceIfc;

@Controller("cadastrarRealizacaoController")
@RequestMapping("/realizacao")
public class RealizacaoController {

	private RealizacaoServiceIfc realizacaoServiceIfc;

	private AtividadeServiceIfc atividadeServiceIfc;

	public AtividadeServiceIfc getAtividadeServiceIfc(){
		return atividadeServiceIfc;
	}

	@Autowired(required = true)
	public void setAtividadeServiceIfc(@Qualifier("atividadeServiceIfc")AtividadeServiceIfc atividadeServiceIfc){
		this.atividadeServiceIfc = atividadeServiceIfc;
	}

	@Autowired(required = true)
	public void setRealizacaoServiceIfc(@Qualifier("realizacaoServiceIfc")RealizacaoServiceIfc realizacaoServiceIfc){
		this.realizacaoServiceIfc = realizacaoServiceIfc;
	}

	@RequestMapping(value="/{codRealizacao}", method = RequestMethod.GET)
	public String getRealizacoes(@PathVariable long codRealizacao, Model model){
		//    	RealizacaoBeans realizacao = this.realizacaoServiceIfc.getRealizacao(codRealizacao);
		//    	model.addAttribute("realizacao", realizacao);

		return "realizacao";
	}

	@RequestMapping(value="/cadastraRealizacao/{codAtividade}",method = RequestMethod.GET)
	public String adiciona(@PathVariable long codAtividade, Model model, HttpServletRequest request) throws IOException,IllegalAccessException{	

		return ("redirect:/realizacao/cadastrarRealizacoes/");
	}
	
    @RequestMapping(value = "/cadastrarRealizacoes", method = RequestMethod.GET)
    public String cadastraRealizacao(Model model, HttpServletRequest request) {
    	return "cadastrar-realizacao";
    }

}
