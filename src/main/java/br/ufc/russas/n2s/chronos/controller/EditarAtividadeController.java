package br.ufc.russas.n2s.chronos.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import br.ufc.russas.n2s.chronos.beans.AtividadeBeans;
import br.ufc.russas.n2s.chronos.beans.UsuarioBeans;
import br.ufc.russas.n2s.chronos.service.AtividadeServiceIfc;
import br.ufc.russas.n2s.chronos.service.UsuarioServiceIfc;

@Controller("editarAtividadeController")
@RequestMapping("/editarAtividade")
public class EditarAtividadeController {

	private AtividadeServiceIfc atividadeServiceIfc;
	private UsuarioServiceIfc usuarioServiceIfc;

	public AtividadeServiceIfc getAtividadeServiceIfc() {
		return atividadeServiceIfc;
	}

	@Autowired(required=true)
	public void setAtividadeServiceIfc(@Qualifier("atividadeServiceIfc") AtividadeServiceIfc atividadeService) {
		this.atividadeServiceIfc = atividadeService;

	}
	public UsuarioServiceIfc getUsuarioService() {
		return usuarioServiceIfc;
	}
	@Autowired(required = true)
	public void setUsuarioServiceIfc(@Qualifier("usuarioServiceIfc")UsuarioServiceIfc usuarioServiceIfc) {
		this.usuarioServiceIfc = usuarioServiceIfc;
	}
	
	
	@RequestMapping(value="/{codAtividade}",method = RequestMethod.GET)
	public String getIndex(@PathVariable long codAtividade, Model model, HttpServletRequest request ) {
		 AtividadeBeans atividade = atividadeServiceIfc.getAtividade(codAtividade);
        request.getSession().setAttribute("atividade", atividade);
		
		return "editar-atividade";
	}
	
	@RequestMapping(value="/{codAtividade}",method = RequestMethod.POST)
	public String atualizaAtividade(@PathVariable long codAtividade, @ModelAttribute("atividade") @Valid AtividadeBeans atividade, BindingResult result, Model model, HttpServletResponse reponse, HttpServletRequest request ) throws  IllegalAccessException{ 
		 AtividadeBeans atividadeBeans = atividadeServiceIfc.getAtividade(codAtividade);
		 HttpSession session = request.getSession();
		 
		 System.out.println("\n\n\n");
		 System.out.println(atividade.getNome());
		 System.out.println(atividade.getDescricao());
		 System.out.println("Tipo"+ atividade.getTipoAtividade().toString());
		 System.out.println("\n\n\n");
		 
		 if(atividade != null) {
			 try {
				 atividadeBeans.setNome(atividade.getNome());
				 atividadeBeans.setDescricao(atividade.getDescricao());
				 atividadeBeans.setSigla(atividade.getSigla());
				 atividadeBeans.setTipoAtividade(atividade.getTipoAtividade());
				 atividadeBeans.setLocal(atividade.getLocal());
				 atividadeBeans.setPreRequisitos(atividade.getPreRequisitos());
				 atividadeBeans.setTotalVagas(atividade.getTotalVagas());
				 atividadeBeans.setTotalVagasComunidade(atividade.getTotalVagasComunidade());
				 atividadeBeans.setTipoPagamento(atividade.getTipoPagamento());
				 atividadeBeans.setOrganizadores(atividade.getOrganizadores());
				 
				 UsuarioBeans usuario = (UsuarioBeans) session.getAttribute("usuarioChronos");
				 this.getAtividadeServiceIfc().setUsuario(usuario);
				 atividadeBeans = this.getAtividadeServiceIfc().atualizaAtividade(atividadeBeans);
				 session.setAttribute("atividade", atividadeBeans);
				 session.setAttribute("mensagem", "Atividade atualizada com sucesso!");
				 session.setAttribute("status", "success");
				 return ("redirect:../atividades/" + atividade.getCodAtividade());
			 }catch (IllegalAccessException e) {
				 model.addAttribute("mensagem", e.getMessage());
				 model.addAttribute("status", "danger");
				 return ("redirect:../atividades/" + atividade.getCodAtividade());
			}
		 }
		return "editar-atividade";
		 
	}
	
	@RequestMapping(value = "/divulga/{codAtividade}", method = RequestMethod.GET)
	public String divulgaAtividade(@PathVariable long codAtividade, HttpServletRequest request) {
	       AtividadeBeans atividade = atividadeServiceIfc.getAtividade(codAtividade);
	        HttpSession session = request.getSession();
	        UsuarioBeans usuario = (UsuarioBeans) session.getAttribute("usuarioChronos");
	        try{
	            atividadeServiceIfc.setUsuario(usuario);
	            atividade.setDivulgada(true);
	            atividade = atividadeServiceIfc.atualizaAtividade(atividade);
	            request.getSession().setAttribute("atividade", atividade);
	            return "redirect:/atividades/" + atividade.getCodAtividade();
	            
	        }catch(IllegalAccessException e){
	            e.printStackTrace();
	            return "redirect:/atividades/" + atividade.getCodAtividade();
	        }catch(Exception e){
	            e.printStackTrace();
	             return "redirect:/atividades/" + atividade.getCodAtividade();
	        }
	    }
}
