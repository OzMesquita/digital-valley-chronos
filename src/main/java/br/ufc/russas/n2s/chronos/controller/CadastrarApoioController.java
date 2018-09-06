package br.ufc.russas.n2s.chronos.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
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
import org.springframework.web.bind.annotation.RequestParam;

import br.ufc.russas.n2s.chronos.beans.ApoioBeans;
import br.ufc.russas.n2s.chronos.beans.AtividadeBeans;
import br.ufc.russas.n2s.chronos.beans.OrganizadorBeans;
import br.ufc.russas.n2s.chronos.beans.RealizacaoBeans;
import br.ufc.russas.n2s.chronos.beans.UsuarioBeans;
import br.ufc.russas.n2s.chronos.model.Atividade;
import br.ufc.russas.n2s.chronos.model.EnumPermissao;
import br.ufc.russas.n2s.chronos.service.AtividadeServiceIfc;
import br.ufc.russas.n2s.chronos.service.UsuarioServiceIfc;

@Controller("cadastrarApoioController")
@RequestMapping("/cadastrarApoio")
public class CadastrarApoioController {
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
		return "cadastrar-apoio";
	}
	
	@RequestMapping(value = "/cadastraApoio/{codAtividade}", method = RequestMethod.POST)
	public String adiciona(@PathVariable long codAtividade,
			@ModelAttribute("apoio") @Valid ApoioBeans apoio, BindingResult result, Model model,
			HttpServletResponse response, HttpServletRequest request) throws IOException, IllegalAccessException {
		HttpSession session = request.getSession();
		UsuarioBeans usuario = (UsuarioBeans) session.getAttribute("usuarioChronos");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		// Pegando DATA e HORA do form
		String[] dataI = request.getParameter("dataPagamento").split("-");
		// DATAS
		LocalDateTime dataPagamento = LocalDateTime.of(Integer.parseInt(dataI[0]), Integer.parseInt(dataI[1]),
				Integer.parseInt(dataI[2]), LocalDateTime.now().getHour(), LocalDateTime.now().getMinute());
		apoio.setDataPagamento(dataPagamento);
		
		System.out.println(apoio.getCodApoio());
		System.out.println(apoio.getLogo());
		System.out.println(apoio.getNomeInstituicao());
		System.out.println(apoio.getSiteInstituicao());
		System.out.println(apoio.getTipoApoio());
		System.out.println(apoio.getValorApoio());
		System.out.println(apoio.getDataPagamento());
		try {
			this.atividadeServiceIfc.setUsuario(usuario);
			AtividadeBeans atividade = this.getAtividadeService().getAtividade(codAtividade);
			atividade.getApoiadores().add(apoio);
			atividade = this.getAtividadeService().atualizaAtividade(atividade);
			session.setAttribute("mensagem", "Apoio cadastrado com sucesso!");
			session.setAttribute("status", "success");
			return ("redirect:/apoiadores/" + codAtividade);

		} catch (IllegalArgumentException e) {
			session.setAttribute("mensagem", e.getMessage());
			session.setAttribute("status", "danger");
			return ("redirect:/apoiadores/" + codAtividade);
		}
	}
}
