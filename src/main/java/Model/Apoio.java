package Model;

import java.time.LocalDate;

public class Apoio {

	private String nomeInstituicao;

	private String logo;

	private String tipoApoio;

	private float valorApoio;

	private LocalDate dataPagamento;

	private String siteInstituicao;

	public String getNomeInstituicao() {
		return nomeInstituicao;
	}

	public void setNomeInstituicao(String nomeInstituicao) {
		if(!vazio(nomeInstituicao))
			this.nomeInstituicao = nomeInstituicao;
		else
			throw new IllegalArgumentException("Erro: o campo nome da instituicao não pode estar vazio");
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
		if(!vazio(tipoApoio))
			this.tipoApoio = tipoApoio;
		else
			throw new IllegalArgumentException("Erro: o campo tipo de apoio não pode estar vazio");
	}

	public float getValorApoio() {
		return valorApoio;
	}

	public void setValorApoio(float valorApoio) {
		this.valorApoio = valorApoio;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getSiteInstituicao() {
		return siteInstituicao;
	}

	public void setSiteInstituicao(String siteInstituicao) {
		this.siteInstituicao = siteInstituicao;
	}
	
	private boolean vazio(String string) {
		if(string==null||string.equals(""))
			return false;
		return true;
	}
}
