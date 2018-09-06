package br.ufc.russas.n2s.chronos.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "apoio")
public class Apoio implements Comparable<Apoio> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codApoio")
	private long codApoio;
	@Column(name = "nomeInstituicao")
	private String nomeInstituicao;
	@Column(name = "logo")
	private String logo;
	@Column(name = "tipoApoio")
	private String tipoApoio;
	@Column(name = "valorApoio")
	private float valorApoio;
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataPagamento;
	@Column(name = "siteInstituicao")
	private String siteInstituicao;

	public Apoio() {
	}

	public Apoio(String nomeInstituicao, String logo, String tipoApoio, float valorApoio, LocalDateTime dataPagamento,
			String siteInstituicao) {
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
		if (codApoio > 0)
			this.codApoio = codApoio;
	}

	public String getNomeInstituicao() {
		return nomeInstituicao;
	}

	public void setNomeInstituicao(String nomeInstituicao) {
		if (isEmpty(nomeInstituicao))
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
		if (isEmpty(tipoApoio))
			throw new IllegalArgumentException("Erro: o campo tipo de apoio nao pode estar vazio.");
		else
			this.tipoApoio = tipoApoio;
	}

	public float getValorApoio() {
		return valorApoio;
	}

	public void setValorApoio(float valorApoio) {
		if (valorApoio < 0)
			throw new IllegalArgumentException("Erro: o campo valor do apoio nao pode ser negativo.");
		else
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
	public int compareTo(Apoio a) {
		return this.getNomeInstituicao().compareTo(a.getNomeInstituicao());
	}

	public static boolean isEmpty(String string) {
		if (string == null || string.equals(""))
			return true;
		return false;
	}
}