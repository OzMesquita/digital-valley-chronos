package br.ufc.russas.n2s.chronos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "inscricao")
public class InscricaoAtividade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codInscricao")
	private long codInscricao;
	@ManyToOne(targetEntity = UsuarioChronos.class)
	@JoinColumn(name = "participante", referencedColumnName = "codUsuario")
	private UsuarioChronos participante;
	@ManyToOne(targetEntity = Atividade.class)
	@JoinColumn(name = "atividade", referencedColumnName = "codAtividade")
	private Atividade atividade;

	public InscricaoAtividade() {
	}

	public long getCodInscricao() {
		return codInscricao;
	}

	public void setCodInscricao(long codInscricao) {
		this.codInscricao = codInscricao;
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

	public Atividade getAtividade() {
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
