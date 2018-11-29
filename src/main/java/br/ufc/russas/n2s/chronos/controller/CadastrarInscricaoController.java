package br.ufc.russas.n2s.chronos.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Iterator;

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
import br.ufc.russas.n2s.chronos.beans.AtividadeBeans;
import br.ufc.russas.n2s.chronos.beans.RealizacaoBeans;
import br.ufc.russas.n2s.chronos.beans.UsuarioBeans;
import br.ufc.russas.n2s.chronos.model.Atividade;
import br.ufc.russas.n2s.chronos.model.Realizacao;
import br.ufc.russas.n2s.chronos.model.exceptions.AtividadeException;
import br.ufc.russas.n2s.chronos.service.AtividadeServiceIfc;
import br.ufc.russas.n2s.chronos.service.UsuarioServiceIfc;

@Controller("cadastrarInscricaoController")
@RequestMapping("/cadastrarInscricao")
public class CadastrarInscricaoController {
	private AtividadeServiceIfc atividadeServiceIfc;
	private UsuarioServiceIfc usuarioServiceIfc;

	public AtividadeServiceIfc getAtividadeService() {
		return atividadeServiceIfc;
	}

	@Autowired(required = true)
	public void setAtividadeServiceIfc(@Qualifier("atividadeServiceIfc") AtividadeServiceIfc atividadeService) {
		this.atividadeServiceIfc = atividadeService;

	}

	public UsuarioServiceIfc getUsuarioServiceIfc() {
		return usuarioServiceIfc;
	}

	@Autowired(required = true)
	public void setUsuarioServiceIfc(@Qualifier("usuarioServiceIfc") UsuarioServiceIfc usuarioServiceIfc) {
		this.usuarioServiceIfc = usuarioServiceIfc;
	}

	@RequestMapping(value = "/{codAtividade}", method = RequestMethod.GET)
	public String getCadastro(@PathVariable long codAtividade, Model model, HttpServletRequest request) {
		return "cadastrar-inscricao";
	}

	@RequestMapping(value = "/cadastraInscricao/{codAtividade}", method = RequestMethod.POST)
	public String adiciona(@PathVariable long codAtividade,
			Model model,
			HttpServletResponse response, HttpServletRequest request) throws IOException, IllegalAccessException {
		HttpSession session = request.getSession();
		UsuarioBeans usuario = (UsuarioBeans) session.getAttribute("usuarioChronos");
		int i = 0;        
        LocalDateTime inicio=null;
        LocalDateTime termino = null;

		try {
			this.atividadeServiceIfc.setUsuario(usuario);
			AtividadeBeans atividade = this.getAtividadeService().getAtividade(codAtividade);		
			
			Iterator<Realizacao> iteratorR = atividade.getPai().getRealizacao().iterator();
			while(iteratorR.hasNext()) {
				Realizacao realizacao = iteratorR.next();        			
				if(i==0){ // Se for a primeira realiza��o
					inicio = realizacao.getHoraInicio();
					i=-1;
				}
				if(!iteratorR.hasNext()) {
					termino = realizacao.getHoraFinal();
					i=0;
				}				 
			} 
			if(termino.isBefore(LocalDateTime.now()) || inicio.isBefore(LocalDateTime.now())) {
				session.setAttribute("mensagem", "N�o foi poss�vel realizar a inscri��o! A atividade j� foi finalizada ou est� em andamento.");
				session.setAttribute("status", "danger");
				return ("redirect:/atividades/" + this.getAtividadeService().getAtividade(codAtividade).getPai().getCodAtividade());
			}else {
				atividade.addParticipante(usuario);
				atividade = this.getAtividadeService().atualizaAtividade(atividade);
				session.setAttribute("mensagem", "Inscri��o realizada com sucesso!");
				session.setAttribute("status", "success");
				
			}
			
			atividade.addParticipante(usuario);
			atividade = this.getAtividadeService().atualizaAtividade(atividade);
			session.setAttribute("mensagem", "Inscri��o realizada com sucesso!");
			session.setAttribute("status", "success");
			return ("redirect:/atividades/" + this.getAtividadeService().getAtividade(codAtividade).getPai().getCodAtividade());
		} catch (IllegalAccessException e) {
			session.setAttribute("mensagem", e.getMessage());
			session.setAttribute("status", "danger");
			return ("redirect:/atividades/" + this.getAtividadeService().getAtividade(codAtividade).getPai().getCodAtividade());
		} catch (AtividadeException e) {
			session.setAttribute("mensagem", e.getMessage());
			session.setAttribute("status", "danger");
			return ("redirect:/atividades/" + this.getAtividadeService().getAtividade(codAtividade).getPai().getCodAtividade());
		}
	}
}
