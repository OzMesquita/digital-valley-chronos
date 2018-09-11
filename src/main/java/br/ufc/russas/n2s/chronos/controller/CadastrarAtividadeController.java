package br.ufc.russas.n2s.chronos.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufc.russas.n2s.chronos.beans.AtividadeBeans;
import br.ufc.russas.n2s.chronos.beans.OrganizadorBeans;
import br.ufc.russas.n2s.chronos.beans.UsuarioBeans;
import br.ufc.russas.n2s.chronos.model.Atividade;
import br.ufc.russas.n2s.chronos.model.EnumPermissao;
import br.ufc.russas.n2s.chronos.model.UsuarioChronos;
import br.ufc.russas.n2s.chronos.service.AtividadeServiceIfc;
import br.ufc.russas.n2s.chronos.service.UsuarioServiceIfc;

@Controller("cadastrarAtividadeController")
@RequestMapping("/cadastrarAtividade")
public class CadastrarAtividadeController {

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

	@RequestMapping(method = RequestMethod.GET)
	public String getCadastro(Model model, HttpServletRequest request) {
		request.getSession().removeAttribute("pai");
		List<UsuarioBeans> organizadores = this.getUsuarioServiceIfc().listaTodosUsuarios();
		model.addAttribute("organizadores", organizadores);
		return "cadastrar-atividade";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String adicionaAtividades(@ModelAttribute("atividade") @Valid AtividadeBeans atividade, BindingResult result,
			Model model, HttpServletResponse response, HttpServletRequest request)
			throws IOException, IllegalAccessException {
		HttpSession session = request.getSession();
		UsuarioBeans usuario = (UsuarioBeans) session.getAttribute("usuarioChronos");
		this.atividadeServiceIfc.setUsuario(usuario);
		String[] codOrganizadores = request.getParameterValues("codOrganizador");
		ArrayList<OrganizadorBeans> organizadores = new ArrayList<>();
		if (codOrganizadores != null) {
			for (String cod : codOrganizadores) {
				OrganizadorBeans organizadorBeans = new OrganizadorBeans();
				UsuarioBeans u = this.getUsuarioServiceIfc().getUsuario(Long.parseLong(cod), 0);
				if (u != null) {
					organizadorBeans.setUsuarioBeans(u);
				}
			}
		}
		atividade = this.getAtividadeService().adicionaAtividade(atividade);
		if (!usuario.getPermissoes().contains(EnumPermissao.ADMINISTRADOR)) {
			usuario.getPermissoes().add(EnumPermissao.ADMINISTRADOR);
		}
		OrganizadorBeans orgBeans = new OrganizadorBeans();
		orgBeans.setUsuarioBeans(usuario);
		orgBeans.setPermissao(EnumPermissao.ADMINISTRADOR);
		atividade.setOrganizadores(organizadores);
		atividade.getOrganizadores().add(orgBeans);
		atividade.setDivulgada(false);
		atividade = this.getAtividadeService().atualizaAtividade(atividade);
		session.setAttribute("mensagem", "Atividade cadastrada com sucesso!");
		session.setAttribute("status", "success");
		return ("redirect:atividades/" + atividade.getCodAtividade());
	}

	@RequestMapping(value = "/subAtividade", method = RequestMethod.POST)
	public String adicionaSubAtividades(@ModelAttribute("atividade") @Valid AtividadeBeans atividade,
			BindingResult result, Model model, HttpServletResponse response, HttpServletRequest request)
			throws IOException, IllegalAccessException {
		HttpSession session = request.getSession();
		UsuarioBeans usuario = (UsuarioBeans) session.getAttribute("usuarioChronos");
		this.atividadeServiceIfc.setUsuario(usuario);
		String[] codOrganizadores = request.getParameterValues("codOrganizador");
		ArrayList<OrganizadorBeans> organizadores = new ArrayList<>();
		if (codOrganizadores != null) {
			for (String cod : codOrganizadores) {
				OrganizadorBeans organizadorBeans = new OrganizadorBeans();
				UsuarioBeans u = this.getUsuarioServiceIfc().getUsuario(Long.parseLong(cod), 0);
				if (u != null) {
					organizadorBeans.setUsuarioBeans(u);
				}
			}
		}
		atividade = this.getAtividadeService().adicionaAtividade(atividade);
		if (!usuario.getPermissoes().contains(EnumPermissao.ADMINISTRADOR)) {
			usuario.getPermissoes().add(EnumPermissao.ADMINISTRADOR);
		}
		if (request.getSession().getAttribute("pai") != null) {
			AtividadeBeans atvBeans = (AtividadeBeans) request.getSession().getAttribute("pai");
			atividade.setPai((Atividade) atvBeans.toBusiness());
		}
		OrganizadorBeans orgBeans = new OrganizadorBeans();
		orgBeans.setUsuarioBeans(usuario);
		orgBeans.setPermissao(EnumPermissao.ADMINISTRADOR);
		atividade.setOrganizadores(organizadores);
		atividade.getOrganizadores().add(orgBeans);
		atividade.setDivulgada(false);
		atividade = this.getAtividadeService().atualizaAtividade(atividade);
		session.setAttribute("mensagem", "Atividade cadastrada com sucesso!");
		session.setAttribute("status", "sucess");
		return ("redirect:/subatividades/" + atividade.getPai().getCodAtividade());
	}
}
