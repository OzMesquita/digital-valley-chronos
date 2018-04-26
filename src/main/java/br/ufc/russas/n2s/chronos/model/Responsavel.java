package br.ufc.russas.n2s.chronos.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.ufc.russas.n2s.chronos.facade.Facade;
import br.ufc.russas.n2s.chronos.model.exceptions.IllegalCodeException;
import model.Pessoa;
import model.Usuario;

@Entity
@Table(name="responsavel")
public class Responsavel extends Pessoa implements Comparable<Pessoa>{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codResponsavel")
	private long codResponsavel;
	
	private String miniCurriculo;

	public Responsavel() {

	}

	public Responsavel(String nome, String cpf, String email, Usuario usuario, LocalDate dataNascimento, String miniCurriculo) {
		super(nome, cpf, email, usuario, dataNascimento);
		this.miniCurriculo = miniCurriculo;
	}

	public long getCodResponsavel() {
		return codResponsavel;
	}
	
	public void setCodResponsavel(long codResponsavel) {
		 if(codResponsavel>0)
	            this.codResponsavel = codResponsavel;
	        else
	            throw new IllegalCodeException("Código do responsável deve ser maior de zero!");
	}
	public String getMiniCurriculo() {
		return miniCurriculo;
	}

	public void setMiniCurriculo(String miniCurriculo) {
		if(!Facade.isEmpty(miniCurriculo))
			throw new IllegalArgumentException("Erro: o campo local não pode estar vazio.");
		else
			this.miniCurriculo = miniCurriculo;
	}

	@Override
	public int compareTo(Pessoa o) {
		return this.getNome().compareTo(o.getNome());
	}
}
