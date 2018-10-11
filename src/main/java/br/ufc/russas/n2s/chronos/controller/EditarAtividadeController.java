package br.ufc.russas.n2s.chronos.controller;

import java.util.Iterator;
import java.util.List;

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
import br.ufc.russas.n2s.chronos.beans.ColaboradorBeans;
import br.ufc.russas.n2s.chronos.beans.UsuarioBeans;
import br.ufc.russas.n2s.chronos.model.Atividade;
import br.ufc.russas.n2s.chronos.model.Colaborador;
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
        if(atividade.getPai()!=null) {
        	request.getSession().setAttribute("colaboradoresselecionados", atividade.getColaboradores());
        	List<Colaborador> colaboradores = atividade.getPai().getColaboradores();
        	for (Iterator<ColaboradorBeans> iterator = atividade.getColaboradores().iterator(); iterator.hasNext();) {
        		long codcolaborador = iterator.next().getCodColaborador();
        		for (Iterator<Colaborador> iterator2 = colaboradores.iterator(); iterator2.hasNext();)
					if(codcolaborador==iterator2.next().getCodColaborador())
						iterator2.remove();
        	}
			request.getSession().setAttribute("colaboradores", colaboradores);
        }
		return "editar-atividade";
	}
	
	@RequestMapping(value="/{codAtividade}",method = RequestMethod.POST)
	public String atualizaAtividade(@PathVariable long codAtividade, @ModelAttribute("atividade") @Valid AtividadeBeans atividade, BindingResult result, Model model, HttpServletResponse reponse, HttpServletRequest request ) throws  IllegalAccessException{ 
		 AtividadeBeans atividadeBeans = atividadeServiceIfc.getAtividade(codAtividade);
		 HttpSession session = request.getSession();
		 String[] colaboradoresDisponiveis = request.getParameterValues("colaboradorInput1");
		 String[] colaboradoresSelecionados = request.getParameterValues("colaboradorInput2");
		 
		 {
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
				 
				 if(atividadeBeans.getPai()!=null) {
					 if(colaboradoresSelecionados!=null)
						 for (Colaborador colaboradorDisp : atividadeBeans.getPai().getColaboradores()) {
							for(String codcolaborador : colaboradoresSelecionados)
								if(colaboradorDisp.getCodColaborador()==Long.parseLong(codcolaborador)) {
									for (Iterator<ColaboradorBeans> iterator = atividadeBeans.getColaboradores().iterator(); iterator.hasNext();)
										if(iterator.next().getCodColaborador()==colaboradorDisp.getCodColaborador())
											iterator.remove();
									atividadeBeans.getColaboradores().add((ColaboradorBeans) new ColaboradorBeans().toBeans(colaboradorDisp));
								}
						 }
					 if(colaboradoresDisponiveis!=null)
						 for(String codcolaboradorDisponivel : colaboradoresDisponiveis)
							for (Iterator<ColaboradorBeans> iterator = atividadeBeans.getColaboradores().iterator(); iterator.hasNext();)
								if(iterator.next().getCodColaborador()==Long.parseLong(codcolaboradorDisponivel))
									iterator.remove();
				 }
				 
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
	
	@RequestMapping(value = "/remover/{codAtividade}", method = RequestMethod.GET)
	public String removeAtividade(@PathVariable long codAtividade, HttpServletRequest request) throws IllegalAccessException {
		AtividadeBeans atividade = atividadeServiceIfc.getAtividade(codAtividade);
		HttpSession session = request.getSession();
		UsuarioBeans usuario = (UsuarioBeans) session.getAttribute("usuarioChronos");
		this.atividadeServiceIfc.setUsuario(usuario);
		if(atividade.getPai() != null) {
			List<Atividade> subAtividadesAUX = atividade.getPai().getSubAtividade();
			
			for (Iterator<Atividade> iterator = subAtividadesAUX.iterator(); iterator.hasNext();) {
				Atividade atvAUX = iterator.next();
				if (atvAUX.getCodAtividade() == codAtividade) {
					iterator.remove();
					
					atividade = this.getAtividadeServiceIfc().atualizaAtividade(atividade);
					
					AtividadeBeans atividadeAUX = new AtividadeBeans();
					atividadeAUX.toBeans(atvAUX);
					try {
						atividadeServiceIfc.removeAtividade(atividadeAUX);
						request.getSession().setAttribute("mensagem", "Atividade removida com sucesso!");
						request.getSession().setAttribute("status", "success");		
						return "redirect:/inicio";
					} catch (Exception e) {
						request.getSession().setAttribute("mensagem", "Erro ao remvoer SubAtividade!");
						request.getSession().setAttribute("status", "danger");	
						return "redirect:/inicio";
					}
				}
			}
		}else {
			try {
				atividadeServiceIfc.removeAtividade(atividade);
				request.getSession().setAttribute("mensagem", "Atividade removida com sucesso!");
				request.getSession().setAttribute("status", "success");
				return "redirect:/inicio";
			} catch (Exception e) {
				request.getSession().setAttribute("mensagem", "Erro ao remover atividade!");
				request.getSession().setAttribute("status", "danger");
				return "redirect:/inicio";
			}
		}
		return null;
	}
}
