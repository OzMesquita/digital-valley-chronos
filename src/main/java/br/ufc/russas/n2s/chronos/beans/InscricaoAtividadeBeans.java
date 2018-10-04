package br.ufc.russas.n2s.chronos.beans;

import br.ufc.russas.n2s.chronos.model.Atividade;
import br.ufc.russas.n2s.chronos.model.InscricaoAtividade;
import br.ufc.russas.n2s.chronos.model.UsuarioChronos;

public class InscricaoAtividadeBeans implements Beans {
	private long codColaborador;
	private UsuarioChronos participante;
	private Atividade atividade;

	public long getCodColaborador() {
		return codColaborador;
	}

	public void setCodColaborador(long codColaborador) {
		this.codColaborador = codColaborador;
	}

	public UsuarioChronos getParticipante() {
		return participante;
	}

	public void setParticipante(UsuarioChronos participante) {
		this.participante = participante;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	@Override
	public Object toBusiness() {
		InscricaoAtividade inscricaoAtividade = new InscricaoAtividade();
		inscricaoAtividade.setParticipante(this.getParticipante());
		inscricaoAtividade.setAtividade(this.getAtividade());
		return inscricaoAtividade;
	}

	@Override
	public Beans toBeans(Object object) {
		if (object == null)
			throw new NullPointerException("O Organizador não pode ser nulo!");
		if (!(object instanceof InscricaoAtividade))
			throw new IllegalArgumentException("O objeto a ser adicionado não é uma Inscricao!");
		InscricaoAtividade inscricaoAtividade = (InscricaoAtividade) object;
		this.setParticipante(inscricaoAtividade.getParticipante());
		this.setAtividade(inscricaoAtividade.getAtividade());
		return this;
	}
}
