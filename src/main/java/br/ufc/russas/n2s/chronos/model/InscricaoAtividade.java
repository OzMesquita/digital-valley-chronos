/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.chronos.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author usuario
 */
@Entity
public class InscricaoAtividade {
	@ManyToOne(targetEntity = UsuarioChronos.class)
	@JoinColumn(name = "participante", referencedColumnName = "codUsuarioChronos")
	private UsuarioChronos participante;
	@ManyToOne(targetEntity = Atividade.class)
	@JoinColumn(name = "atividade", referencedColumnName = "codAtividade")
	private Atividade atividade;

	public InscricaoAtividade() {
	}

	public UsuarioChronos getParticipante() {
		return participante;
	}

	public void setParticipante(UsuarioChronos participante) {
		if (participante != null) {
			this.participante = participante;
		} else {
			throw new IllegalArgumentException("Participante n„o pode ser nulo!");
		}
	}

	public Atividade geAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		if (atividade != null) {
			this.atividade = atividade;

		} else {
			throw new IllegalArgumentException("Atividade n„o pode ser nula!");
		}
	}
	/*
	 * public boolean isCanditado(UsuarioChronos usuario) { for (Participante
	 * participante : getCandidatos()) { if
	 * (participante.getCandidato().equals(usuario)) { return true; } } return
	 * false; }
	 * 
	 * @Override public boolean isParticipante(Participante participante) { if
	 * (participante != null) { if (this.getCandidatos().contains(participante)) {
	 * return true; } } else { throw new
	 * IllegalArgumentException("Participante n√£o pode ser nulo!"); } return false;
	 * }
	 * 
	 * @Override public boolean isParticipante(UsuarioChronos participante){
	 * for(Participante p : this.getCandidatos()){
	 * if(p.getCandidato().equals(participante)) { return true; } } return false; }
	 * 
	 * @Override public List<Object[]> getParticipantes () { List<Object[]>
	 * participantes = Collections.synchronizedList(new ArrayList<Object[]>()); for
	 * (Participante p : getCandidatos()) { Object[] participante = {p,0,0};
	 * participantes.add(participante); } return participantes; }
	 */
}
