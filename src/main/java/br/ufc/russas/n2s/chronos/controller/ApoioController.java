package br.ufc.russas.n2s.chronos.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.ufc.russas.n2s.chronos.beans.AtividadeBeans;
import br.ufc.russas.n2s.chronos.service.AtividadeServiceIfc;
import br.ufc.russas.n2s.chronos.util.Constantes;

@Controller("apoioController")
@RequestMapping("/apoiadores")
public class ApoioController {

	private AtividadeServiceIfc atividadeServiceIfc;

	public AtividadeServiceIfc getAtividadeServiceIfc() {
		return atividadeServiceIfc;
	}

	@Autowired(required = true)
	public void setAtividadeServiceIfc(@Qualifier("atividadeServiceIfc") AtividadeServiceIfc atividadeServiceIfc) {
		this.atividadeServiceIfc = atividadeServiceIfc;
	}

	@RequestMapping(value = "/{codAtividade}", method = RequestMethod.GET)
	public String getApoiadores(@PathVariable long codAtividade, Model model) {
		AtividadeBeans atividade = this.atividadeServiceIfc.getAtividade(codAtividade);
		model.addAttribute("atividade", atividade);
		return "apoiadores";
	}

	@RequestMapping(value = "/images/uploads/{imageID}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> getPageImage(HttpServletRequest request) {
		try {
			HttpHeaders headers = new HttpHeaders();
			String fileName = request.getServletPath().replace("apoiadores/images/uploads/", "");
			File file = new File(Constantes.getLogoImgApoio() + fileName);
			InputStream is = new BufferedInputStream(new FileInputStream(file));
			String mimeType = URLConnection.guessContentTypeFromStream(is);
			headers.setContentType(MediaType.valueOf(mimeType));
			return new ResponseEntity<byte[]>(IOUtils.toByteArray(is), headers, HttpStatus.OK);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
