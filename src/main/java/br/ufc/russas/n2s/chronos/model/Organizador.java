package br.ufc.russas.n2s.chronos.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.ufc.russas.n2s.chronos.model.exceptions.IllegalCodeException;
import model.Pessoa;

@Entity
@Table(name="organizador")
public class Organizador extends Pessoa implements Comparable<Pessoa>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codOrganizador")
	
	private long codOrganizador;
	
	@ManyToMany(targetEntity = Atividade.class, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "atividades_organizador", joinColumns = {@JoinColumn(name = "organizador", referencedColumnName = "codOrganizador")},
    inverseJoinColumns = {@JoinColumn(name = "atividade", referencedColumnName = "codAtividade")})
	private List<Atividade> atividadeRelacionadas;
	
	@Enumerated(EnumType.ORDINAL)
	private EnumPermissao permissao;
	
	public Organizador() {
	}

	public Organizador(EnumPermissao permissao) {
		this.permissao = permissao;
	}

	public long getCodOrganizador() {
		return codOrganizador;
	}
	
	public void setCodOrganizador(long codOrganizador) {
		 if(codOrganizador>0)
	            this.codOrganizador = codOrganizador;
	        else
	            throw new IllegalCodeException("Código do organizador deve ser maior de zero!");
	}
	
	
	public List<Atividade> getAtividadeRelacionadas() {
		return atividadeRelacionadas;
	}

	public void setAtividadeRelacionadas(List<Atividade> atividadeRelacionadas) {
		this.atividadeRelacionadas = atividadeRelacionadas;
	}

	public void addAtividadeRelacionada(Atividade atividadeRelacionada) {
		if(atividadeRelacionada == null)
			throw new IllegalArgumentException("Erro: o campo atividade relacionada não pode estar vazio.");
		else
			atividadeRelacionadas.add(atividadeRelacionada);
	}
	
	public EnumPermissao getPermissao() {
		return permissao;
	}

	public void setPermissao(EnumPermissao permissao) {
		if(permissao == null)
			throw new IllegalArgumentException("Erro: o campo permissão não pode estar vazio");
		else
			this.permissao = permissao;
	}

	@Override
	public int compareTo(Pessoa o) {
		return this.getNome().compareTo(o.getNome());
	}

}
