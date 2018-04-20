package Beans;

import Model.Responsavel;

public class ResponsavelBeans implements Beans {

	private int codResponsavel;
	private String miniCurriculo;

	
	public int getCodResponsavel() {
		return codResponsavel;
	}

	public void setCodResponsavel(int codResponsavel) {
		this.codResponsavel = codResponsavel;
	}
	
	public String getMiniCurriculo() {
		return miniCurriculo;
	}

	public void setMiniCurriculo(String miniCurriculo) {
		this.miniCurriculo = miniCurriculo;
	}

	@Override
	public Object toBusiness() {
		Responsavel responsavel = new Responsavel();

		if(this.getCodResponsavel() > 0){
			responsavel.setCodResponsavel(this.getCodResponsavel());
		}
		responsavel.setMiniCurriculo(this.getMiniCurriculo());

		return responsavel;
	}
	
	@Override
	public Beans toBeans(Object object) {
        if(object == null)
        	throw new NullPointerException("O Responsavel não pode ser nula!");
        if(!(object instanceof Responsavel))
        	throw new IllegalArgumentException("O objeto a ser adicionado não é uma Responsavel!");
		
        Responsavel responsavel = (Responsavel) object;
		
		this.setCodResponsavel(responsavel.getCodResponsavel());
		this.setMiniCurriculo(responsavel.getMiniCurriculo());
		
		return this;
	}	
}
