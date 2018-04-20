package Beans;

import java.time.LocalDateTime;
import Model.Realizacao;

public class RealizacaoBeans implements Beans {

	private int codRealizacao;
	private LocalDateTime horaInicio;
	private LocalDateTime horaFinal;

	public RealizacaoBeans() {
	}

	public RealizacaoBeans(int codRealizacao, LocalDateTime horaInicio, LocalDateTime horaFinal) {
		this.codRealizacao = codRealizacao;
		this.horaInicio = horaInicio;
		this.horaFinal = horaFinal;
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
		this.horaInicio = horaInicio;
	}
	public LocalDateTime getHoraFinal() {
		return horaFinal;
	}
	public void setHoraFinal(LocalDateTime horaFinal) {
		this.horaFinal = horaFinal;
	}

	@Override
	public Object toBusiness() {
		Realizacao realizacao = new Realizacao();
		
		realizacao.setCodRealizacao(this.getCodRealizacao());
		realizacao.setHoraInicio(this.getHoraInicio());
		realizacao.setHoraFinal(this.getHoraFinal());

		return realizacao;
	}
	
	@Override
	public Beans toBeans(Object object) {
        if(object == null)
        	throw new IllegalArgumentException("O objeto a ser adicionado não é uma Realizacao!");
        if(!(object instanceof Realizacao))
        	throw new NullPointerException("A Realizacao não pode ser nula!");
		
        Realizacao realizacao = (Realizacao) object;
		
        this.setCodRealizacao(realizacao.getCodRealizacao());
		this.setHoraInicio(realizacao.getHoraInicio());
		this.setHoraFinal(realizacao.getHoraFinal());   
		
		return this;
	}
}
