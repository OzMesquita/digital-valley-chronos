package br.ufc.russas.n2s.chronos.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

import br.ufc.russas.n2s.chronos.beans.AtividadeBeans;
import br.ufc.russas.n2s.chronos.beans.UsuarioBeans;
import br.ufc.russas.n2s.chronos.service.AtividadeServiceIfc;
import br.ufc.russas.n2s.chronos.service.UsuarioServiceIfc;

@Controller("editarAtividadeController")
@RequestMapping("/editarAtividade")
public class EditarAtividadeController {

	private AtividadeServiceIfc atividadeServiceIfc;
	private UsuarioServiceIfc usuarioServiceIfc;

	public AtividadeServiceIfc getAtividadeService() {
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
	
	
	@RequestMapping(value="/{codAtividade}",method = RequestMethod.POST)
	public String getIndex(@PathVariable long codAtividade, Model model, HttpServletRequest request ) {
		 AtividadeBeans atividade = atividadeServiceIfc.getAtividade(codAtividade);
        request.getSession().setAttribute("atividade", atividade);
		
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
