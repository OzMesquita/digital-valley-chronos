package br.ufc.russas.n2s.chronos.model;

public enum EnumPermissao {
	ADMINISTRADOR(1), APOIO(2), PARTICIPANTE(3);
	private int nivel;

	EnumPermissao() {
	}

	EnumPermissao(int nivel) {
		setNivel(nivel);
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		if (nivel >= 1 && nivel <= 3) {
			this.nivel = nivel;
		} else {
			throw new IllegalArgumentException(
					"Não foi possível alterar a permissão do usuário!");
		}
	}

	public static EnumPermissao getValor(int valor) {
		if (valor >= 1 && valor <= 3) {
			EnumPermissao p = null;
			if (valor == 1) {
				p = EnumPermissao.ADMINISTRADOR;
			} else if (valor == 2) {
				p = EnumPermissao.APOIO;
			} else if (valor == 3) {
				p = EnumPermissao.PARTICIPANTE;
			}
			return p;
		} else {
			throw new IllegalArgumentException(
					"Não foi possível alterar a permissão do usuário!");
		}
	}
}