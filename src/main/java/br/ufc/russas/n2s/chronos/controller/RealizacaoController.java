package br.ufc.russas.n2s.chronos.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
import br.ufc.russas.n2s.chronos.service.UsuarioServiceIfc;

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

	public RealizacaoServiceIfc getRealizacaoServiceIfc() {
		return realizacaoServiceIfc;
	}
	
	@Autowired(required = true)
	public void setRealizacaoServiceIfc(@Qualifier("realizacaoServiceIfc")RealizacaoServiceIfc realizacaoServiceIfc){
		this.realizacaoServiceIfc = realizacaoServiceIfc;
	}

	@RequestMapping(value="/{codAtividade}", method = RequestMethod.GET)
	public String getRealizacoes(@PathVariable long codAtividade, Model model){
		AtividadeBeans atividadeBeans = this.atividadeServiceIfc.getAtividade(codAtividade);
		List<RealizacaoBeans> realizacao = atividadeBeans.getRealizacao();
		model.addAttribute("realizacao", realizacao);

		return "realizacao";
	}

	@RequestMapping(value="/cadastraRealizacao/{codAtividade}",method = RequestMethod.POST)
	public String adiciona(@PathVariable long codAtividade, @ModelAttribute("realizacao") @Valid RealizacaoBeans realizacao, BindingResult result, Model model, HttpServletResponse response, HttpServletRequest request) throws IOException,IllegalAccessException{	
		HttpSession session = request.getSession();
		UsuarioBeans usuario = (UsuarioBeans) session.getAttribute("usuarioChronos");
		this.atividadeServiceIfc.setUsuario(usuario);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		//Pegando DATA e HORA do form
		String[] dataI = request.getParameter("dataInicio").split("-");
		String[] dataF = request.getParameter("dataFinal").split("-");
		
		String[] horaI = request.getParameter("horaInicio").split(":");
		String[] horaF = request.getParameter("horaFinal").split(":");
		
		//DATAS
		LocalDateTime dataInicial = LocalDateTime.of(Integer.parseInt(dataI[0]), Integer.parseInt(dataI[1]), Integer.parseInt(dataI[2]), Integer.parseInt(horaI[0]),Integer.parseInt(horaI[1]));	
		LocalDateTime dataFinal = LocalDateTime.of(Integer.parseInt(dataF[0]), Integer.parseInt(dataF[1]), Integer.parseInt(dataF[2]), Integer.parseInt(horaF[0]),Integer.parseInt(horaF[1]));

		realizacao.setHoraInicio(dataInicial);
		realizacao.setHoraFinal(dataFinal);
		AtividadeBeans atividade = this.getAtividadeServiceIfc().getAtividade(codAtividade);
		atividade.getRealizacao().add(realizacao);

		atividade = this.getAtividadeServiceIfc().atualizaAtividade(atividade);

		session.setAttribute("mensagem","Realizacao cadastrada com sucesso!");
		session.setAttribute("status", "success");
		
		return ("redirect:/realizacao/"+codAtividade);
	}
	
//    @RequestMapping(value = "/cadastrarRealizacoes", method = RequestMethod.GET)
//    public String cadastraRealizacao(Model model, HttpServletRequest request) {
//    	return "realizacao";
//    }

}
