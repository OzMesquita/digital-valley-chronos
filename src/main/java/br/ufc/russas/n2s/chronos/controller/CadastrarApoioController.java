package br.ufc.russas.n2s.chronos.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import br.ufc.russas.n2s.chronos.beans.ApoioBeans;
import br.ufc.russas.n2s.chronos.beans.AtividadeBeans;
import br.ufc.russas.n2s.chronos.beans.UsuarioBeans;
import br.ufc.russas.n2s.chronos.service.AtividadeServiceIfc;
import br.ufc.russas.n2s.chronos.service.UsuarioServiceIfc;
//import br.ufc.russas.n2s.chronos.util.Constantes;
import util.Constantes;


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
	@RequestMapping( value="teste",method = RequestMethod.POST)
	public String adicionaApoio(@ModelAttribute("apoio") @Valid ApoioBeans apoio, BindingResult result, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
    	UsuarioBeans usuario = (UsuarioBeans) session.getAttribute("usuarioChronos");
    	AtividadeBeans atividade = (AtividadeBeans)session.getAttribute("pai");
    	System.out.println(Constantes.getLogosDir());
  		System.out.println("\n\n");
  		String name = Constantes.getDocumentsDir()+File.separator+"apoiadores"+File.separator+atividade.getNome()+File.separator+apoio.getNomeInstituicao();
    	//String name = Constantes.getLogosDir()+File.separator+"Apoiadores_"+atividade.getNome()+File.separator+apoio.getNomeInstituicao()+".png";
    	  System.out.println(name);
  		System.out.println("\n\n");
    	try {
    		//logo
    		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
    		MultipartFile multipartFile = multipartRequest.getFile("logoApoio");
    		File file = this.convert(multipartFile);
    		
    		
    		
    		File diretorio  = new File(name);
    		
    	
    		
    		
    		
    		BufferedImage imagem = ImageIO.read(new File(Constantes.getDocumentsDir()+File.separator+"apoiadores"+File.separator+atividade.getNome()+File.separator+apoio.getNomeInstituicao()));
    		//BufferedImage imagem = ImageIO.read(new File(Constantes.getLogosDir()+File.separator+multipartFile.getName()+".png"));
    		// fazer algo com a imagem...
    		ImageIO.write(imagem, "PNG", new File(Constantes.getLogosDir()+File.separator+multipartFile.getName()));
 
    		
	    //	if( usuario != null) {
    		this.getAtividadeService().setUsuario(usuario);
    		
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
	
	@RequestMapping( method = RequestMethod.POST)
	public String adicionarApoio(@ModelAttribute("apoio") @Valid ApoioBeans apoio, BindingResult result, Model model, HttpServletRequest request, @RequestParam("logoApoio") MultipartFile logoApoio) {
		HttpSession session = request.getSession();
    	UsuarioBeans usuario = (UsuarioBeans) session.getAttribute("usuarioChronos");
    	AtividadeBeans atividade = (AtividadeBeans)session.getAttribute("pai");
    	try {
  		//String name = Constantes.getDocumentsDir()+File.separator+"apoiadores"+File.separator+atividade.getNome()+File.separator+apoio.getNomeInstituicao();
    	//String name = Constantes.getLogosDir()+File.separator+"Apoiadores_"+atividade.getNome()+File.separator+apoio.getNomeInstituicao()+".png";
  		if (!logoApoio.isEmpty()) {
			
				byte[] bytes = logoApoio.getBytes();

				// Creating the directory to store file
				//String rootPath = System.getProperty("catalina.home");
				String rootPath = Constantes.getDocumentsDir()+ File.separator+"apoiadores";
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + apoio.getNomeInstituicao()+".png");
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				
				apoio.setLogo(serverFile.getAbsolutePath());
		}
	
  		
  		
  		
  		
  		
    	//try {
    		
	    //	if( usuario != null) {
    		this.getAtividadeService().setUsuario(usuario);
    		
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
	    	
    	} catch (Exception e) {
    		e.printStackTrace();
            model.addAttribute("mensagem", e.getMessage());
            model.addAttribute("status", "danger");
            return ("cadastrar-apoio");
		}
    }
	
	public File convert(MultipartFile file)
	{   try {
		    File convFile = new File(file.getOriginalFilename());
		    convFile.createNewFile(); 
		    FileOutputStream fos = new FileOutputStream(convFile); 
		    fos.write(file.getBytes());
		    fos.close();
		    return convFile;
	}catch (Exception e) {
		// TODO: handle exception
		return null;
	}
    
	}
	
}
