package br.ufc.russas.n2s.chronos.model;

public class Colaborador implements Comparable<Colaborador>{

	private long CodColaborador;
	private String nome;
	private String funcao;
	
	public Colaborador( String nome, String funcao) {
		this.nome = nome;
		this.funcao = funcao;
	}
	
	public Colaborador() {

	}

	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		if (!isEmpty(nome))
			this.nome = nome;
		else
			throw new IllegalArgumentException("Erro: o campo nome não pode estar vazio");
	}


	public String getFuncao() {
		return funcao;
	}


	public void setFuncao(String funcao) {
		if (!isEmpty(funcao))
			this.funcao = funcao;
		else
			throw new IllegalArgumentException("Erro: o campo função não pode estar vazio");
	}


	public void setCodColaborador(long codColaborador) {
		CodColaborador = codColaborador;
	}


	public long getCodColaborador() {
		return this.CodColaborador;
	}


	@Override
	public int compareTo(Colaborador o) {
		return this.getNome().compareTo(o.getNome());
	}

	public static boolean isEmpty(String string) {
		if (string == null || string.equals(""))
			return true;
		return false;
	}
}
