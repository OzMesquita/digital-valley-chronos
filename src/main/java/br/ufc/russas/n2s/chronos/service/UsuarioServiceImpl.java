package br.ufc.russas.n2s.chronos.service;

import br.ufc.russas.n2s.chronos.beans.UsuarioBeans;
import br.ufc.russas.n2s.chronos.dao.UsuarioDAOIfc;
import br.ufc.russas.n2s.chronos.model.EnumPermissao;
import br.ufc.russas.n2s.chronos.model.UsuarioChronos;
import br.ufc.russas.n2s.chronos.model.UsuarioChronosProxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class UsuarioServiceImpl implements UsuarioServiceIfc {
	private UsuarioDAOIfc usuarioDAOIfc;
	private UsuarioBeans usuario;

	public UsuarioDAOIfc getUsuarioDAOIfc() {
		return usuarioDAOIfc;
	}

	@Autowired(required = true)
	public void setUsuarioDAOIfc(@Qualifier("usuarioDAOIfc") UsuarioDAOIfc usuarioDAOIfc) {
		this.usuarioDAOIfc = usuarioDAOIfc;
	}

	@Override
	public void setUsuario(UsuarioBeans usuario) {
		this.usuario = usuario;
	}

	@Override
	public UsuarioBeans adicionaUsuario(UsuarioBeans usuario) {
		return (UsuarioBeans) new UsuarioBeans()
				.toBeans(this.getUsuarioDAOIfc().adicionaUsuario((UsuarioChronos) usuario.toBusiness()));
	}

	@Override
	public UsuarioBeans atualizaUsuario(UsuarioBeans usuario) {
		return (UsuarioBeans) new UsuarioBeans()
				.toBeans(this.getUsuarioDAOIfc().atualizaUsuario((UsuarioChronos) usuario.toBusiness()));
	}

	@Override
	public void removeUsuario(UsuarioBeans usuario) {
		this.getUsuarioDAOIfc().removeUsuario((UsuarioChronos) usuario.toBusiness());
	}

	@Override
	public List<UsuarioBeans> listaTodosUsuarios() {
		List<UsuarioChronos> result = this.getUsuarioDAOIfc().listaUsuarios(new UsuarioChronos());
		List<UsuarioBeans> usuarios = Collections.synchronizedList(new ArrayList<UsuarioBeans>());
		for (UsuarioChronos usuario : result) {
			usuarios.add((UsuarioBeans) new UsuarioBeans().toBeans(usuario));
		}
		return usuarios;
	}

	@Override
	public UsuarioBeans getUsuario(long codUsuario, long codUsuarioControleDeAcesso) {
		UsuarioChronos usuario = new UsuarioChronos();
		usuario.setCodUsuario(codUsuario);
		usuario.setCodUsuarioControleDeAcesso(codUsuarioControleDeAcesso);
		UsuarioChronos u = this.getUsuarioDAOIfc().getUsuario(usuario);
		if (u != null) {
			return (UsuarioBeans) new UsuarioBeans().toBeans(u);
		} else {
			return null;
		}
	}

	@Override
	public UsuarioBeans getUsuarioControleDeAcesso(long codUsuarioControleDeAcesso) {
		UsuarioChronos usuario = new UsuarioChronos();
		usuario.setCodUsuarioControleDeAcesso(codUsuarioControleDeAcesso);
		UsuarioChronos u = this.getUsuarioDAOIfc().getUsuarioControleDeAcesso(usuario);
		if (u != null) {
			return (UsuarioBeans) new UsuarioBeans().toBeans(u);
		} else {
			return null;
		}
	}

	@Override
	public void adicionaNivel(UsuarioBeans usuario, EnumPermissao permissao) throws IllegalAccessException {
		UsuarioChronos u = (UsuarioChronos) this.usuario.toBusiness();
		UsuarioChronosProxy up = new UsuarioChronosProxy(u);
		usuario = (UsuarioBeans) usuario.toBeans(up.adicionaNivel((UsuarioChronos) usuario.toBusiness(), permissao));
		atualizaUsuario(usuario);
	}

	@Override
	public void removeNivel(UsuarioBeans usuario, EnumPermissao permissao) throws IllegalAccessException {
		UsuarioChronos u = (UsuarioChronos) this.usuario.toBusiness();
		UsuarioChronosProxy up = new UsuarioChronosProxy(u);
		usuario = (UsuarioBeans) usuario.toBeans(up.removeNivel((UsuarioChronos) usuario.toBusiness(), permissao));
		atualizaUsuario(usuario);
	}

	public List<UsuarioBeans> listaAvaliadores() {
		List<UsuarioBeans> avaliadores = Collections.synchronizedList(new ArrayList<UsuarioBeans>());
		List<UsuarioBeans> resultado = this.listaTodosUsuarios();
		for (UsuarioBeans ub : resultado) {
			if (ub.getPermissoes().contains(EnumPermissao.ADMINISTRADOR)) {
				avaliadores.add(ub);
			}
		}
		return avaliadores;
	}

	@Override
	public void atualizaNiveis(UsuarioBeans usuario, List<EnumPermissao> permisoesAtualizadas) throws IllegalAccessException {
		UsuarioChronos u = (UsuarioChronos) this.usuario.toBusiness();
		UsuarioChronosProxy up = new UsuarioChronosProxy(u);
        usuario  = (UsuarioBeans) usuario.toBeans(up.atualizaNiveis((UsuarioChronos)usuario.toBusiness(), permisoesAtualizadas));
        atualizaUsuario(usuario);
		
	}

	@Override
	public Object BuscaUsuariosPorNome(String nome) {
		List<UsuarioChronos> result = this.getUsuarioDAOIfc().BuscaUsuariosPorNome(nome);
        List<UsuarioBeans> usuarios = Collections.synchronizedList(new ArrayList<UsuarioBeans>());
        for(UsuarioChronos usuario : result){
            usuarios.add((UsuarioBeans) new UsuarioBeans().toBeans(usuario));
        }
        return usuarios;
	}

	
}