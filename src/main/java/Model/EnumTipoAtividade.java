package Model;

public enum EnumTipoAtividade {
	ENCONTRO(1),
	SEMANA(2),
	MINICURSO(3),
	CURSO(4),
	PALESTRA(5),
	WORKSHOP(6),
	VISITATECNICA(7);

	private int valorTipo;
	
	EnumTipoAtividade(int valor) {
		if(valor<1&&valor>7)
			valorTipo = 2;
		else
			valorTipo = valor;	
	}

	public int getValorTipo() {
		return valorTipo;
	}
	
	public static String value(Integer valor){
		switch(valor){
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
	

}
