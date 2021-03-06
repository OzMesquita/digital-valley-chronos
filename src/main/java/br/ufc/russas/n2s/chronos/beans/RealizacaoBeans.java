package br.ufc.russas.n2s.chronos.beans;

import java.time.LocalDateTime;
import br.ufc.russas.n2s.chronos.model.Realizacao;

public class RealizacaoBeans implements Beans {

	private long codRealizacao;
	private LocalDateTime horaInicio;
	private LocalDateTime horaFinal;

	public long getCodRealizacao() {
		return codRealizacao;
	}
	public void setCodRealizacao(long codRealizacao) {
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
        	throw new IllegalArgumentException("O objeto a ser adicionado n�o � uma Realizacao!");
        if(!(object instanceof Realizacao))
        	throw new NullPointerException("A Realizacao n�o pode ser nula!");
		
        Realizacao realizacao = (Realizacao) object;
		
        this.setCodRealizacao(realizacao.getCodRealizacao());
		this.setHoraInicio(realizacao.getHoraInicio());
		this.setHoraFinal(realizacao.getHoraFinal());   
		
		return this;
	}
}
