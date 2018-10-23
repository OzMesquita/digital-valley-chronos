package br.ufc.russas.n2s.chronos.controller;

import java.io.IOException;
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
import br.ufc.russas.n2s.chronos.beans.UsuarioBeans;
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

		try {
			this.atividadeServiceIfc.setUsuario(usuario);
			AtividadeBeans atividade = this.getAtividadeService().getAtividade(codAtividade);
			atividade.getParticipantes().add(usuario);
			atividade = this.getAtividadeService().atualizaAtividade(atividade);
			session.setAttribute("mensagem", "Inscrição realizada com sucesso!");
			session.setAttribute("status", "success");
			return ("redirect:/atividades/" + codAtividade);
		} catch (IllegalAccessException e) {
			session.setAttribute("mensagem", e.getMessage());
			session.setAttribute("status", "danger");
			return ("redirect:/atividades/" + codAtividade);
		}
	}
}
