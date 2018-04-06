package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Realizacao {
	private LocalDateTime horaInicio;
	private LocalDateTime horaFinal;
	private LocalDate data;
	
	public Realizacao(LocalDateTime horaInicio, LocalDateTime horaFinal, LocalDate data) {
		this.horaInicio = horaInicio;
		this.horaFinal = horaFinal;
		this.data = data;
	}
	public LocalDateTime getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(LocalDateTime horaInicio) {
		if(horaInicio!=null)
			this.horaInicio = horaInicio;
		else
			throw new IllegalArgumentException("Erro: o campo hora de inicio n�o pode estar vazio");
	}
	public LocalDateTime getHoraFinal() {
		return horaFinal;
	}
	public void setHoraFinal(LocalDateTime horaFinal) {
		if(horaFinal!=null)
			this.horaFinal = horaFinal;
		else
			throw new IllegalArgumentException("Erro: o campo hora final n�o pode estar vazio");
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		if(data!=null)
			this.data = data;
		else
			throw new IllegalArgumentException("Erro: o campo data n�o pode estar vazio");
	}
	
}
