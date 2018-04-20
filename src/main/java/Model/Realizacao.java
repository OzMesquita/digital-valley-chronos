package Model;

import java.time.LocalDateTime;

public class Realizacao implements Comparable<Realizacao> {
	
	private int codRealizacao;
	private LocalDateTime horaInicio;
	private LocalDateTime horaFinal;

	public Realizacao() {
		
	}
	
	public Realizacao(LocalDateTime horaInicio, LocalDateTime horaFinal) {
		setHoraInicio(horaInicio);
		setHoraFinal(horaFinal);
	}

	public int getCodRealizacao() {
		return codRealizacao;
	}

	public void setCodRealizacao(int codRealizacao) {
		this.codRealizacao = codRealizacao;
	}

	public LocalDateTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalDateTime horaInicio) {
		if(horaInicio == null)
			throw new IllegalArgumentException("Erro: o campo da hora inicial não pode estar vazio.");
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
			throw new IllegalArgumentException("Erro: o campo hora final não pode estar vazio.");
		else if(horaFinal.isEqual(horaInicio))
			throw new IllegalArgumentException("Erro: o campo hora final não pode ser igual ao campo da hora inicial.");
		else if(horaFinal.isBefore(horaInicio))
			throw new IllegalArgumentException("Erro: o campo hora final não pode ser antes do campo da hora inicial.");			
		else
			this.horaFinal = horaFinal;
	}

	@Override
	public int compareTo(Realizacao outraRealizacao) {
		if (this.horaInicio.isAfter(outraRealizacao.getHoraInicio()))
			return 1;
		else if (this.horaInicio.isBefore(outraRealizacao.getHoraInicio()))
			return -1;
		else
			return 0;
	}
}