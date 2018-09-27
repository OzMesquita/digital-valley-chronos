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
import br.ufc.russas.n2s.chronos.beans.UsuarioBeans;
import br.ufc.russas.n2s.chronos.service.AtividadeServiceIfc;
import br.ufc.russas.n2s.chronos.service.ColaboradorServiceIfc;
import br.ufc.russas.n2s.chronos.service.UsuarioServiceIfc;

@Controller("editarColaboradorController")
@RequestMapping("/editarColaborador")
public class EditarColaboradorController {
	private AtividadeServiceIfc atividadeServiceIfc;
	private UsuarioServiceIfc usuarioServiceIfc;
	private ColaboradorServiceIfc colaboradorServiceIfc;

	public AtividadeServiceIfc getAtividadeService() {
		return atividadeServiceIfc;
	}

	@Autowired(required = true)
	public void setAtividadeServiceIfc(@Qualifier("atividadeServiceIfc") AtividadeServiceIfc atividadeService) {
		this.atividadeServiceIfc = atividadeService;

	}
	
	public ColaboradorServiceIfc getColaboradorServiceIfc() {
		return colaboradorServiceIfc;
	}
	
	@Autowired(required = true)
	public void setColaboradorServiceIfc(@Qualifier("colaboradorServiceIfc") ColaboradorServiceIfc colaboradorServiceIfc) {
		this.colaboradorServiceIfc = colaboradorServiceIfc;
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

	@RequestMapping(value = "/{codAtividade}&{codColaborador}", method = RequestMethod.POST)
	public String editaColaborador(@PathVariable long codAtividade, @PathVariable long codColaborador,
			@ModelAttribute("colaborador") @Valid ColaboradorBeans colaborador, BindingResult result, Model model,
			HttpServletResponse response, HttpServletRequest request) throws IOException, IllegalAccessException {
		HttpSession session = request.getSession();
		UsuarioBeans usuario = (UsuarioBeans) session.getAttribute("usuarioChronos");
		this.atividadeServiceIfc.setUsuario(usuario);
		AtividadeBeans atividadeBeans = this.getAtividadeService().getAtividade(codAtividade);
		if (atividadeBeans != null) {
			try {
				for (Iterator<ColaboradorBeans> iterator = atividadeBeans.getColaboradores().iterator(); iterator
						.hasNext();) {
					ColaboradorBeans colaboradorAUX = iterator.next();
					if (colaboradorAUX.getCodColaborador() == codColaborador) {
						colaboradorAUX.setNome(colaborador.getNome());
						colaboradorAUX.setFuncao(colaborador.getFuncao());
						break;
					}
				}
				atividadeBeans = this.getAtividadeService().atualizaAtividade(atividadeBeans);
				session.setAttribute("mensagem", "Colaborador cadastrado com sucesso!");
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
	
	@RequestMapping(value = "/remove/{codAtividade}&{codColabordor}", method = RequestMethod.POST)
	public String removerApoio(@PathVariable long codAtividade, @PathVariable long codColaborador, Model model,
			HttpServletRequest request) throws IllegalAccessException {
		HttpSession session = request.getSession();
		UsuarioBeans usuario = (UsuarioBeans) session.getAttribute("usuarioChronos");
		this.atividadeServiceIfc.setUsuario(usuario);
		AtividadeBeans atividadeBeans = atividadeServiceIfc.getAtividade(codAtividade);
		for (Iterator<ColaboradorBeans> iterator = atividadeBeans.getColaboradores().iterator(); iterator.hasNext();) {
			ColaboradorBeans colaboradorAUX = iterator.next();
			if (colaboradorAUX.getCodColaborador() == codColaborador) {
				iterator.remove();
				atividadeBeans = this.getAtividadeService().atualizaAtividade(atividadeBeans);
				colaboradorServiceIfc.atualizaColaborador(colaboradorAUX);
				colaboradorServiceIfc.removeColaborador(colaboradorAUX);
			}
		}
		
		session.setAttribute("mensagem", "Apoio removido com sucesso!");
		session.setAttribute("status", "success");
		return ("redirect:/apoiadores/" + codAtividade);
	}
}
