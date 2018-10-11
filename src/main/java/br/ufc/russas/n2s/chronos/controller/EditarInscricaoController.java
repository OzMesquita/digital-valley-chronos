package br.ufc.russas.n2s.chronos.controller;

import java.io.IOException;
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
import br.ufc.russas.n2s.chronos.beans.ColaboradorBeans;
import br.ufc.russas.n2s.chronos.beans.InscricaoAtividadeBeans;
import br.ufc.russas.n2s.chronos.beans.UsuarioBeans;
import br.ufc.russas.n2s.chronos.model.Atividade;
import br.ufc.russas.n2s.chronos.model.UsuarioChronos;
import br.ufc.russas.n2s.chronos.service.AtividadeServiceIfc;
import br.ufc.russas.n2s.chronos.service.ColaboradorServiceIfc;
import br.ufc.russas.n2s.chronos.service.UsuarioServiceIfc;

@Controller("editarInscricaoController")
@RequestMapping("/editarInscricao")
public class EditarInscricaoController {
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

	@RequestMapping(value = "/{codAtividade}&{codColaborador}", method = RequestMethod.GET)
	public String getCadastro(@PathVariable long codAtividade, @PathVariable long codColaborador, Model model,
			HttpServletRequest request) {
		return "colaborador";
	}
	//Funciona quando o model e o Bean de atividade for alterado para refletir a inscrição
	@RequestMapping(value = "/{codAtividade}&{codInscricao}/editar", method = RequestMethod.POST)
	public String editaColaborador(@PathVariable long codAtividade, @PathVariable long codInscricao,
			@ModelAttribute("participante") @Valid UsuarioChronos participante, @ModelAttribute("atividade") @Valid Atividade atividade, BindingResult result, Model model,
			HttpServletResponse response, HttpServletRequest request) throws IOException, IllegalAccessException {
		HttpSession session = request.getSession();
		UsuarioBeans usuario = (UsuarioBeans) session.getAttribute("usuarioChronos");
		this.atividadeServiceIfc.setUsuario(usuario);
		AtividadeBeans atividadeBeans = this.getAtividadeService().getAtividade(codAtividade);
		if (atividadeBeans != null) {
			try {
				for (Iterator<InscricaoAtividadeBeans> iterator = atividadeBeans.getInscricoes().iterator(); iterator
						.hasNext();) {
					InscricaoAtividadeBeans inscricaoAUX = iterator.next();
					if (inscricaoAUX.getCodInscricao() == codInscricao) {
						inscricaoAUX.setParticipante(participante);
						inscricaoAUX.setAtividade(atividade);
						break;
					}
				}
				atividadeBeans = this.getAtividadeService().atualizaAtividade(atividadeBeans);
				session.setAttribute("mensagem", "Inscricao editada com sucesso!");
				session.setAttribute("status", "success");
				return ("redirect:/colaboradores/" + codAtividade);
			}

			catch (IllegalArgumentException e) {
				session.setAttribute("mensagem", e.getMessage());
				session.setAttribute("status", "danger");
				return ("redirect:/colaboradores/" + codAtividade);
			}
		}
		return null;
	}
}
