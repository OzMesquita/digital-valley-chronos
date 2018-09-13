package br.ufc.russas.n2s.chronos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.russas.n2s.chronos.service.AtividadeServiceIfc;
import br.ufc.russas.n2s.chronos.service.UsuarioServiceIfc;

@Controller("inscricaoController")
@RequestMapping("/inscricao")
public class InscricaoController {

	private AtividadeServiceIfc atividadeServiceIfc;
	private UsuarioServiceIfc usuarioServiceIfc;

	public AtividadeServiceIfc getAtividadeServiceIfc() {
		return atividadeServiceIfc;
	}

	@Autowired(required = true)
	public void setAtividadeServiceIfc(@Qualifier("atividadeServiceIfc") AtividadeServiceIfc atividadeServiceIfc) {
		this.atividadeServiceIfc = atividadeServiceIfc;
	}

	public UsuarioServiceIfc getUsuarioServiceIfc() {
		return usuarioServiceIfc;
	}

	@Autowired(required = true)
	public void setUsuarioServiceIfc(@Qualifier("usuarioServiceIfc") UsuarioServiceIfc usuarioServiceIfc) {
		this.usuarioServiceIfc = usuarioServiceIfc;
	}

}
