package Model;

import java.time.LocalDateTime;

public class Realizacao {
	private LocalDateTime horaInicio;
	private LocalDateTime horaFinal;

	
	public Realizacao(LocalDateTime horaInicio, LocalDateTime horaFinal) {
		setHoraInicio(horaInicio);
		setHoraFinal(horaFinal);
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
}