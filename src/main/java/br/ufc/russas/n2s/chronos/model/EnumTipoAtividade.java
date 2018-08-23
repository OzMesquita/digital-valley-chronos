package br.ufc.russas.n2s.chronos.model;

public enum EnumTipoAtividade {
	ENCONTRO(1), SEMANA(2), MINICURSO(3), CURSO(4), PALESTRA(5), WORKSHOP(6), VISITA_TÉCNICA(7);
	private int valorTipo;

	EnumTipoAtividade(int valor) {
		if (valor < 1 && valor > 7)
			valorTipo = 2;
		else
			valorTipo = valor;
	}

	public int getValorTipo() {
		return valorTipo;
	}

	public static String value(Integer valor) {
		switch (valor) {
		case 1:
			return "Encontro";
		case 2:
			return "Semana";
		case 3:
			return "Minicurso";
		case 4:
			return "Curso";
		case 5:
			return "Palestra";
		case 6:
			return "Workshop";
		case 7:
			return "Visita Técnica";
		default:
			return "";
		}
	}

	public static EnumTipoAtividade valorEnumPeloNome(String nome) {
		for (EnumTipoAtividade tipo : EnumTipoAtividade.values())
			if (tipo.name().equals(nome)) {
				return tipo;
			}
		return null; // not found
	}
}