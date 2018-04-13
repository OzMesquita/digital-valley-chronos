package Model;

import java.util.ArrayList;

import model.Pessoa;

public class Organizador extends Pessoa{

	private ArrayList<Atividade> atividadeRelacionadas;
	private EnumNivel nivel;
	
	public Organizador(EnumNivel nivel) {
		this.nivel = nivel;
	}

	public ArrayList<Atividade> getAtividadeRelacionadas() {
		return atividadeRelacionadas;
	}

	public void setAtividadeRelacionadas(ArrayList<Atividade> atividadeRelacionadas) {
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

}
