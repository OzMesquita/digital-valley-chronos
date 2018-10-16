package br.ufc.russas.n2s.chronos.model;

public class AtividadeProxy {
	private UsuarioChronos usuario;

	public AtividadeProxy(UsuarioChronos usuario) {
		setUsuario(usuario);
	}

	public UsuarioChronos getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioChronos usuario) {
		if (usuario != null) {
			this.usuario = usuario;
		} else {
			throw new NullPointerException("Usuário do Chronos não pode ser vazio!");
		}
	}

	public Atividade adicionaAtividade(Atividade atividade) throws IllegalAccessException {
		if (this.getUsuario().getPermissoes().contains(EnumPermissao.ADMINISTRADOR)) {
			return atividade.adicionaAtividade();
		} else {
			throw new IllegalAccessException(
					"Você não é o responsável por esta atividade: <b> ".concat(atividade.getNome()).concat("</b>"));
		}
	}

	public Atividade atualizaAtividade(Atividade atividade) throws IllegalAccessException {
		if (this.getUsuario().getPermissoes().contains(EnumPermissao.ADMINISTRADOR)) {
			return atividade.atualizaAtividade();
		}else if(this.getUsuario().getPermissoes().contains(EnumPermissao.PARTICIPANTE)) {
			return atividade.atualizaAtividade();
		}
		else {
			throw new IllegalAccessException(
					"Você não é o responsável por esta atividade: <b> ".concat(atividade.getNome()).concat("</b>"));
		}
	}
}