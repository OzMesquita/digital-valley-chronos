package br.ufc.russas.n2s.chronos.Model;

import java.util.List;
import model.Pessoa;

public class Organizador extends Pessoa implements Comparable<Pessoa>{

	private int codOrganizador;
	private List<Atividade> atividadeRelacionadas;
	private EnumNivel nivel;
	
	public Organizador() {
		
	}
	
	public Organizador(int codOrganizador, EnumNivel nivel) {
		this.nivel = nivel;
		setCodOrganizador(codOrganizador);
	}

	public int getCodOrganizador() {
		return codOrganizador;
	}

	public void setCodOrganizador(int codOrganizador) {
		if(codOrganizador < 0)
			throw new IllegalArgumentException("Erro: o campo codigo do organizador nao pode estar vazio");
		else
			this.codOrganizador = codOrganizador;
	}

	public List<Atividade> getAtividadeRelacionadas() {
		return atividadeRelacionadas;
	}

	public void setAtividadeRelacionadas(List<Atividade> atividadeRelacionadas) {
		this.atividadeRelacionadas = atividadeRelacionadas;
	}

	public void addAtividadeRelacionada(Atividade atividadeRelacionada) {
		if(atividadeRelacionada == null)
			throw new IllegalArgumentException("Erro: o campo atividade relacionada não pode estar vazio.");
		else
			atividadeRelacionadas.add(atividadeRelacionada);
	}
	
	public EnumNivel getNivel() {
		return nivel;
	}

	public void setNivel(EnumNivel nivel) {
		if(nivel == null)
			throw new IllegalArgumentException("Erro: o campo local não pode estar vazio");
		else
			this.nivel = nivel;
	}

	@Override
	public int compareTo(Pessoa o) {
		return this.getNome().compareTo(o.getNome());
	}

}
