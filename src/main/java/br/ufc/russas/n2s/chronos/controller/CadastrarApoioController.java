package br.ufc.russas.n2s.chronos.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.mapping.Value;
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
import br.ufc.russas.n2s.chronos.beans.UsuarioBeans;
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

	@Autowired(required=true)
	public void setAtividadeServiceIfc(@Qualifier("atividadeServiceIfc") AtividadeServiceIfc atividadeService) {
		this.atividadeServiceIfc = atividadeService;

	}
	public UsuarioServiceIfc getUsuarioServiceIfc() {
		return usuarioServiceIfc;
	}
	@Autowired(required = true)
	public void setUsuarioServiceIfc(@Qualifier("usuarioServiceIfc")UsuarioServiceIfc usuarioServiceIfc) {
		this.usuarioServiceIfc = usuarioServiceIfc;
	}

	@RequestMapping(method = RequestMethod.GET)
    public String getCadastro( Model model, HttpServletRequest request) {
    	AtividadeBeans atividade = (AtividadeBeans) request.getSession().getAttribute("pai");
        model.addAttribute("pai", atividade);
        return "cadastrar-apoio";
    }
	//value="/{codAtividade}",
	//@PathVariable long codAtividade,
	@RequestMapping( method = RequestMethod.POST)
	public String adicionaApoio(@ModelAttribute("apoio") @Valid ApoioBeans apoio, BindingResult result, Model model, HttpServletRequest request) {
		System.out.println("\n\nAQUI penultimo \n\n");
		System.out.println(apoio.getNomeInstituicao());
		System.out.println("\n\nAQUI penultimo \n\n");
		HttpSession session = request.getSession();
    	UsuarioBeans usuario = (UsuarioBeans) session.getAttribute("usuarioChronos");
    	try {
	    //	if( usuario != null) {
    		this.getAtividadeService().setUsuario(usuario);
    		AtividadeBeans atividade = (AtividadeBeans)session.getAttribute("pai");
	    		//AtividadeBeans atividade = this.getAtividadeService().getAtividade(codAtividade);
	    		//for (OrganizadorBeans organizador : atividade.getOrganizadores()) {
	    			//if (organizador.getUsuarioBeans().getCodUsuario() == usuario.getCodUsuario()) {
	    				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	    				String[] dataP = request.getParameter("dataPagamento").split("-");
	    				//String[] horaP = request.getParameter("dataPagamento").split(":");
	    				//LocalDate pagamento = LocalDate.parse(request.getParameter("dataPagamento"), formatter);
	    				LocalDateTime dataPagamento = LocalDateTime.of(Integer.parseInt(dataP[0]), Integer.parseInt(dataP[1]), Integer.parseInt(dataP[2]),0,0);//, Integer.parseInt(horaP[0]),Integer.parseInt(horaP[1]));
	    				apoio.setDataPagamento(dataPagamento);
	    				atividade.getApoiadores().add(apoio);
	    				atividade = this.getAtividadeService().atualizaAtividade(atividade);
	    				session.setAttribute("mensagem", "Apoio cadastrado com sucesso!");
	    	            session.setAttribute("status", "success");
	    	            return ("redirect:/apoiadores/" + atividade.getCodAtividade());
	    		//	} else {
	    		//		System.out.println("prim \n\n");
	    		//		model.addAttribute("mensagem", "Você não é um organizador desta atividade!");
	    	    //        model.addAttribute("status", "danger");
	    		//	}
	    	//	}
	    //	} else {
	    //		System.out.println("penultimo \n\n");
	   // 		model.addAttribute("mensagem", "Você não esta autenticado no sistema!");
	    //        model.addAttribute("status", "danger");
	    //	}
    	} catch (Exception e) {
    		e.printStackTrace();
            model.addAttribute("mensagem", e.getMessage());
            model.addAttribute("status", "danger");
            return ("cadastrar-apoio");
		}
    }
	
	
}
