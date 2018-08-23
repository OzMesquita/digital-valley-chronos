package br.ufc.russas.n2s.chronos.model;

public enum EnumNivel {
	ADMINISTRADOR(1), APOIO(2);
	private int valorNivel;

	EnumNivel(int valor) {
		if (valor < 1 && valor > 2)
			valorNivel = 2;
		else
			valorNivel = valor;
	}

	public int getValorNivel() {
		if (valorNivel == 0) {
			return 2;
		}
		return valorNivel;
	}

	public static String value(Integer valor) {
		switch (valor) {
		case 1:
			return "Administrador";
		case 2:
			return "Apoio";
		default:
			return "";
		}
	}
}