package br.ufc.russas.n2s.chronos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "colaborador")
public class Colaborador implements Comparable<Colaborador>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codColaborador")
	private long codColaborador;
	@Column(name = "nome")
	private String nome;
	@Column(name = "funcao")
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
		this.codColaborador = codColaborador;
	}


	public long getCodColaborador() {
		return this.codColaborador;
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
