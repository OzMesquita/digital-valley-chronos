package br.ufc.russas.n2s.chronos.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
			throw new IllegalArgumentException("Participante não pode ser nulo!");
		}
	}

	public Atividade geAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		if (atividade != null) {
			this.atividade = atividade;

		} else {
			throw new IllegalArgumentException("Atividade não pode ser nula!");
		}
	}
}
