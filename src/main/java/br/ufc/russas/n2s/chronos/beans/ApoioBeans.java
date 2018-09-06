package br.ufc.russas.n2s.chronos.beans;

import java.time.LocalDateTime;

import br.ufc.russas.n2s.chronos.model.Apoio;
public class ApoioBeans implements Beans{
	private long codApoio;
	private String nomeInstituicao;
	private String logo;
	private String tipoApoio;
	private float valorApoio;
	private LocalDateTime dataPagamento;
	private String siteInstituicao;
	public long getCodApoio() {
		return codApoio;
	}
	public void setCodApoio(long codApoio) {
		this.codApoio = codApoio;
	}
	
	public String getNomeInstituicao() {
		return nomeInstituicao;
	}

	public void setNomeInstituicao(String nomeInstituicao) {
		this.nomeInstituicao = nomeInstituicao;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getTipoApoio() {
		return tipoApoio;
	}

	public void setTipoApoio(String tipoApoio) {
		this.tipoApoio = tipoApoio;
	}

	public float getValorApoio() {
		return valorApoio;
	}

	public void setValorApoio(float valorApoio) {
		this.valorApoio = valorApoio;
	}

	public LocalDateTime getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDateTime dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getSiteInstituicao() {
		return siteInstituicao;
	}

	public void setSiteInstituicao(String siteInstituicao) {
		this.siteInstituicao = siteInstituicao;
	}

	@Override
	public Object toBusiness() {
		Apoio apoio = new Apoio();
		apoio.setCodApoio(this.getCodApoio());
		apoio.setNomeInstituicao(this.getNomeInstituicao());
		apoio.setLogo(this.getLogo());
		apoio.setTipoApoio(this.getTipoApoio());
		apoio.setValorApoio(this.getValorApoio());
		apoio.setDataPagamento(this.getDataPagamento());
		apoio.setSiteInstituicao(this.getSiteInstituicao());
		return apoio;
	}

	@Override
	public Beans toBeans(Object object) {
        if(object == null)
        	throw new NullPointerException("O Apoio não pode ser nulo!");
        if(!(object instanceof Apoio))
        	throw new IllegalArgumentException("O objeto a ser adicionado não é um Apoio!");
        Apoio apoio = (Apoio) object;
        this.setCodApoio(apoio.getCodApoio());
		this.setNomeInstituicao(apoio.getNomeInstituicao());
		this.setLogo(apoio.getLogo());
		this.setTipoApoio(apoio.getTipoApoio());
		this.setValorApoio(apoio.getValorApoio());
		this.setDataPagamento(apoio.getDataPagamento());
		this.setSiteInstituicao(apoio.getSiteInstituicao());
		return this;
	}
}