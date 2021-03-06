package br.ufc.russas.n2s.chronos.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="realizacao")
public class Realizacao {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codRealizacao")
	private long codRealizacao;
	
	private LocalDateTime horaInicio;
	
	private LocalDateTime horaFinal;
	
	public Realizacao() {
	}

	public Realizacao(LocalDateTime horaInicio, LocalDateTime horaFinal) {
		setHoraInicio(horaInicio);
		setHoraFinal(horaFinal);
	}
	
	public long getCodRealizacao() {
		return codRealizacao;
	}
	public void setCodRealizacao(long codRealizacao) {
		if(codRealizacao>0) {
			this.codRealizacao = codRealizacao;
		}
	}
	
	public LocalDateTime getHoraInicio() {
		return horaInicio;
	}
	
	public void setHoraInicio(LocalDateTime horaInicio) {
		if(horaInicio == null)
			throw new IllegalArgumentException("Erro: o campo da hora inicial n�o pode estar vazio.");
		else if(horaInicio.isBefore(LocalDateTime.now()))
			throw new IllegalArgumentException("Erro: o campo da hora inicial deve ser posterior a data atual.");
		else
			this.horaInicio = horaInicio;		
	}
	
	public LocalDateTime getHoraFinal() {
		return horaFinal;
	}
	
	public void setHoraFinal(LocalDateTime horaFinal) {
		if(horaFinal == null)
			throw new IllegalArgumentException("Erro: o campo hora final n�o pode estar vazio.");
		else if(horaFinal.isEqual(horaInicio))
			throw new IllegalArgumentException("Erro: o campo hora final n�o pode ser igual ao campo da hora inicial.");
		else if(horaFinal.isBefore(horaInicio))
			throw new IllegalArgumentException("Erro: o campo hora final n�o pode ser antes do campo da hora inicial.");			
		else
			this.horaFinal = horaFinal;
	}	
}