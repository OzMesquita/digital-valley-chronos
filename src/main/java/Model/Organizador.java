package Model;

import java.util.ArrayList;

import model.Pessoa;

public class Organizador extends Pessoa{

	private ArrayList<Atividade> atividadeRelacionadas;
	private EnumNivel nivel;
	
	public ArrayList<Atividade> getAtividadeRelacionadas() {
		return atividadeRelacionadas;
	}

	public void setAtividadeRelacionadas(ArrayList<Atividade> atividadeRelacionadas) {
		this.atividadeRelacionadas = atividadeRelacionadas;
	}

	public EnumNivel getNivel() {
		return nivel;
	}

	public void setNivel(EnumNivel nivel) {
		this.nivel = nivel;
	}

}
