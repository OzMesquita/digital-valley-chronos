package Model;

public enum EnumTipoPagamento {
	GRATUITO(1),
	DINHEIRO(2),
	DOACAO(3);

	private int valorTipo;
	
	EnumTipoPagamento(int valor) {
		if(valor<1&&valor>7)
			valorTipo = 1;
		else
			valorTipo = valor;	
	}

	public int getValorTipo() {
		return valorTipo;
	}
	
	public static String value(Integer valor){
		switch(valor){
		case 1:
			return "Gratuito";
		case 2:
			return "Dinheiro";
		case 3:
			return "Doacao de Item";
		default:
			return "";
		}
	
	}
}
