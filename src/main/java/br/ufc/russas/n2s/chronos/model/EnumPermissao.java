/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.chronos.model;

/**
 *
 * @author N2S
 */
public enum EnumPermissao {
	ADMINISTRADOR(1), APOIO(2);
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
		if (nivel >= 1 && nivel <= 2) {
			this.nivel = nivel;
		} else {
			throw new IllegalArgumentException(
					"Critério de avaliação deve ser maior igual a um e menor igual a quatro!");
		}
	}

	public static EnumPermissao getValor(int valor) {
		if (valor >= 1 && valor <= 2) {
			EnumPermissao p = null;
			if (valor == 1) {
				p = EnumPermissao.ADMINISTRADOR;
			} else if (valor == 2) {
				p = EnumPermissao.APOIO;
			}
			return p;
		} else {
			throw new IllegalArgumentException(
					"Critério de avaliação deve ser maior igual a um e menor igual a quatro!");
		}
	}
}