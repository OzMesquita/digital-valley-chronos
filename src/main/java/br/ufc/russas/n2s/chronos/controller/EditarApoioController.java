package br.ufc.russas.n2s.chronos.controller;

import java.io.File;
import java.io.FileOutputStream;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.ufc.russas.n2s.chronos.beans.ApoioBeans;
import br.ufc.russas.n2s.chronos.beans.AtividadeBeans;
import br.ufc.russas.n2s.chronos.beans.ApoioBeans;
import br.ufc.russas.n2s.chronos.beans.UsuarioBeans;
import br.ufc.russas.n2s.chronos.service.ApoioServiceIfc;
import br.ufc.russas.n2s.chronos.service.AtividadeServiceIfc;
import br.ufc.russas.n2s.chronos.service.UsuarioServiceIfc;
import br.ufc.russas.n2s.chronos.util.Constantes;

@Controller("editarApoioController")
@RequestMapping("/editarApoio")
public class EditarApoioController {
	private AtividadeServiceIfc atividadeServiceIfc;
	private UsuarioServiceIfc usuarioServiceIfc;
	private ApoioServiceIfc apoioServiceIfc;

	public AtividadeServiceIfc getAtividadeService() {
		return atividadeServiceIfc;
	}

	@Autowired(required = true)
	public void setAtividadeServiceIfc(@Qualifier("atividadeServiceIfc") AtividadeServiceIfc atividadeService) {
		this.atividadeServiceIfc = atividadeService;

	}

	public ApoioServiceIfc getApoioServiceIfc() {
		return apoioServiceIfc;
	}
	
	@Autowired(required = true)
	public void setApoioServiceIfc(@Qualifier("apoioServiceIfc") ApoioServiceIfc apoioServiceIfc) {
		this.apoioServiceIfc = apoioServiceIfc;
	}
	
	public UsuarioServiceIfc getUsuarioServiceIfc() {
		return usuarioServiceIfc;
	}

	@Autowired(required = true)
	public void setUsuarioServiceIfc(@Qualifier("usuarioServiceIfc") UsuarioServiceIfc usuarioServiceIfc) {
		this.usuarioServiceIfc = usuarioServiceIfc;
	}

	@RequestMapping(value = "/remove/{codAtividade}&{codApoio}", method = RequestMethod.POST)
	public String removerApoio(@PathVariable long codAtividade, @PathVariable long codApoio, Model model,
			HttpServletRequest request) throws IllegalAccessException {
		HttpSession session = request.getSession();
		UsuarioBeans usuario = (UsuarioBeans) session.getAttribute("usuarioChronos");
		this.atividadeServiceIfc.setUsuario(usuario);
		AtividadeBeans atividadeBeans = atividadeServiceIfc.getAtividade(codAtividade);
		for (Iterator<ApoioBeans> iterator = atividadeBeans.getApoiadores().iterator(); iterator.hasNext();) {
			ApoioBeans apoioAUX = iterator.next();
			if (apoioAUX.getCodApoio() == codApoio) {
				iterator.remove();
				atividadeBeans = this.getAtividadeService().atualizaAtividade(atividadeBeans);
				apoioServiceIfc.atualizaApoio(apoioAUX);
				apoioServiceIfc.removeApoio(apoioAUX);
			}
		}
		
		session.setAttribute("mensagem", "Apoio removido com sucesso!");
		session.setAttribute("status", "success");
		return ("redirect:/apoiadores/" + codAtividade);
	}
	
	@RequestMapping(value="/{codAtividade}&{codApoio}",method = RequestMethod.GET)
	public String getIndex(@PathVariable long codAtividade, @PathVariable long codApoio, Model model, HttpServletRequest request ) {
		ApoioBeans apoioBeans;
		apoioBeans = apoioServiceIfc.getApoio(codApoio);
		request.getSession().setAttribute("apoio", apoioBeans);
		return "editar-apoio";
	}
	
	@RequestMapping(value = "/{codAtividade}&{codApoio}", method = RequestMethod.POST)
	public String adiciona(@PathVariable long codAtividade,@PathVariable long codApoio,
			@ModelAttribute("apoio") @Valid ApoioBeans apoio, BindingResult result, Model model,
			HttpServletResponse response, HttpServletRequest request, @RequestParam ("logo") MultipartFile logo) throws IOException, IllegalAccessException {
		
		AtividadeBeans atividadeBeans = atividadeServiceIfc.getAtividade(codAtividade);
		HttpSession session = request.getSession();
		UsuarioBeans usuario = (UsuarioBeans) session.getAttribute("usuarioChronos");
		this.getAtividadeService().setUsuario(usuario);

		// Pegando DATA e HORA do form
		String[] dataI = request.getParameter("dataPagamento").split("-");
		// DATAS
		LocalDateTime dataPagamento = LocalDateTime.of(Integer.parseInt(dataI[0]), Integer.parseInt(dataI[1]),
				Integer.parseInt(dataI[2]), LocalDateTime.now().getHour(), LocalDateTime.now().getMinute());
		apoio.setDataPagamento(dataPagamento);
		if (atividadeBeans != null) {
			try {
				for (Iterator<ApoioBeans> iterator = atividadeBeans.getApoiadores().iterator(); iterator
						.hasNext();) {
					ApoioBeans apoioAUX = iterator.next();
					if (apoioAUX.getCodApoio() == codApoio) {
						apoioAUX.setDataPagamento(dataPagamento);
						File dir = new File (Constantes.getLogoImgApoio()+File.separator);
						
						if(!dir.isDirectory()) {
							dir.mkdirs();
						}
						
				        if (!logo.isEmpty()) {
				        	File convFile = null;
				        	if(logo.getContentType().equals("image/png")) {
				        		convFile = File.createTempFile(apoio.getNomeInstituicao()+"_"+codAtividade, ".png", dir);
				        	}
				        	else if (logo.getContentType().equals("image/jpeg")) {
				        		convFile = File.createTempFile(apoio.getNomeInstituicao()+"_"+codAtividade, ".jpeg", dir);
							}
				            FileOutputStream fos = new FileOutputStream(convFile); 
				            fos.write(logo.getBytes());
				            fos.close();
				            apoio.setLogo(convFile.getAbsolutePath());
				        }
						apoioAUX.setNomeInstituicao(apoio.getNomeInstituicao());
						apoioAUX.setSiteInstituicao(apoio.getSiteInstituicao());
						apoioAUX.setTipoApoio(apoio.getTipoApoio());
						apoioAUX.setValorApoio(apoio.getValorApoio());
						break;
					}
				}
				
				atividadeBeans = this.getAtividadeService().atualizaAtividade(atividadeBeans);

				session.setAttribute("apoio", atividadeBeans.getApoiadores());
				session.setAttribute("mensagem", "Apoiador atualizado com sucesso!");
				session.setAttribute("status", "success");
				return ("redirect:/apoiadores/" + codAtividade);
			} catch (IllegalAccessException e) {
				model.addAttribute("mensagem", e.getMessage());
				model.addAttribute("status", "danger");
				return ("redirect:/apoiadores/" + codAtividade);
			}
		}
		return null;
	}

}
