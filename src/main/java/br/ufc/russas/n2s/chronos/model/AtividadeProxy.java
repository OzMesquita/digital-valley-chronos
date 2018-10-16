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
			throw new NullPointerException("Usu�rio do Chronos n�o pode ser vazio!");
		}
	}

	public Atividade adicionaAtividade(Atividade atividade) throws IllegalAccessException {
		if (this.getUsuario().getPermissoes().contains(EnumPermissao.ADMINISTRADOR)) {
			return atividade.adicionaAtividade();
		} else {
			throw new IllegalAccessException(
					"Voc� n�o � o respons�vel por esta atividade: <b> ".concat(atividade.getNome()).concat("</b>"));
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
					"Voc� n�o � o respons�vel por esta atividade: <b> ".concat(atividade.getNome()).concat("</b>"));
		}
	}
}