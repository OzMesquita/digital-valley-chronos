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
		this.horaInicio = horaInicio;
	}
	public LocalDateTime getHoraFinal() {
		return horaFinal;
	}
	public void setHoraFinal(LocalDateTime horaFinal) {
		this.horaFinal = horaFinal;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	
}
