package br.ufc.russas.n2s.chronos.Model;

import java.time.LocalDate;
import br.ufc.russas.n2s.chronos.Facade.Facade;

public class Apoio implements Comparable<Apoio>{

	private int codApoio;
	
	private String nomeInstituicao;

	private String logo;

	private String tipoApoio;

	private float valorApoio;

	private LocalDate dataPagamento;

	private String siteInstituicao;

	public Apoio() {
		
	}
	
	public Apoio(int codApoio,String nomeInstituicao, String logo, String tipoApoio, float valorApoio, LocalDate dataPagamento, String siteInstituicao) {
		setDataPagamento(dataPagamento);
		setLogo(logo);
		setNomeInstituicao(nomeInstituicao);
		setSiteInstituicao(siteInstituicao);
		setTipoApoio(tipoApoio);
		setValorApoio(valorApoio);
		setCodApoio(codApoio);
	}

	public int getCodApoio() {
		return codApoio;
	}

	public void setCodApoio(int codApoio) {
		if(codApoio < 0)
			throw new IllegalArgumentException("Erro: o campo codigo do apoio nao pode estar vazio");
		else
			this.codApoio = codApoio;
	}

	public String getNomeInstituicao() {
		return nomeInstituicao;
	}

	public void setNomeInstituicao(String nomeInstituicao) {
		if(Facade.isEmpty(nomeInstituicao))
			throw new IllegalArgumentException("Erro: o campo nome da instituicao nao pode estar vazio");
		else
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
		if(Facade.isEmpty(tipoApoio))
			throw new IllegalArgumentException("Erro: o campo tipo de apoio nao pode estar vazio.");
		else
			this.tipoApoio = tipoApoio;
	}

	public float getValorApoio() {
		return valorApoio;
	}

	public void setValorApoio(float valorApoio) {
		if(valorApoio < 0) 
			throw new IllegalArgumentException("Erro: o campo valor do apoio nao pode ser negativo.");
		else
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

	@Override
	public int compareTo(Apoio a) {
		return this.getNomeInstituicao().compareTo(a.getNomeInstituicao());
	}
}
