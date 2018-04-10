package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Realizacao {
	private LocalDateTime horaInicio;
	private LocalDateTime horaFinal;
	private LocalDate data;
	
	public Realizacao(LocalDateTime horaInicio, LocalDateTime horaFinal, LocalDate data) {
		this.data = data;
		this.horaInicio = horaInicio;
		this.horaFinal = horaFinal;
		
	}
	
	public LocalDateTime getHoraInicio() {
		return horaInicio;
	}
	
	public void setHoraInicio(LocalDateTime horaInicio) {
		if(horaInicio == null)
			throw new IllegalArgumentException("Erro: o campo hora de inicio não pode estar vazio.");
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
	
	public LocalDate getData() {
		return data;
	}
	
	public void setData(LocalDate data) {
		if(data!=null)
			this.data = data;
		else
			throw new IllegalArgumentException("Erro: o campo data não pode estar vazio");
	}
	
}