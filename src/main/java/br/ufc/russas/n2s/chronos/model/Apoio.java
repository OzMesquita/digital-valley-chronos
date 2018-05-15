package br.ufc.russas.n2s.chronos.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import br.ufc.russas.n2s.chronos.model.exceptions.IllegalCodeException;

@Entity
@Table(name="apoio")
public class Apoio implements Comparable<Apoio>{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codApoio")
	private long codApoio;
	
	private String nomeInstituicao;

	private String logo;

	private String tipoApoio;

	private float valorApoio;

	private LocalDate dataPagamento;

	private String siteInstituicao;

	public Apoio() {
	}

	public Apoio(String nomeInstituicao, String logo, String tipoApoio, float valorApoio, LocalDate dataPagamento, String siteInstituicao) {
		setDataPagamento(dataPagamento);
		setLogo(logo);
		setNomeInstituicao(nomeInstituicao);
		setSiteInstituicao(siteInstituicao);
		setTipoApoio(tipoApoio);
		setValorApoio(valorApoio);
	}
	
	public long getCodApoio() {
		return codApoio;
	}
	
	public void setCodApoio(long codApoio) {
		 if(codApoio>0)
	            this.codApoio = codApoio;
	        else
	            throw new IllegalCodeException("Código do apoio deve ser maior de zero!");
	}
	

	public String getNomeInstituicao() {
		return nomeInstituicao;
	}

	public void setNomeInstituicao(String nomeInstituicao) {
		if(isEmpty(nomeInstituicao))
			this.nomeInstituicao = nomeInstituicao;
		else
			throw new IllegalArgumentException("Erro: o campo nome da instituicao nao pode estar vazio");
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
		if(isEmpty(tipoApoio))
			this.tipoApoio = tipoApoio;
		else
			throw new IllegalArgumentException("Erro: o campo tipo de apoio nao pode estar vazio.");
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
	public static boolean isEmpty(String string) {
		if(string==null||string.equals(""))
			return true;
		return false;
	}
}
