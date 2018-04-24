package br.ufc.russas.n2s.chronos.Model;

import java.time.LocalDate;
import br.ufc.russas.n2s.chronos.Facade.Facade;
import model.Pessoa;
import model.Usuario;

public class Responsavel extends Pessoa implements Comparable<Pessoa>{
	
	private int codResponsavel;
	private String miniCurriculo;

	public Responsavel() {
		
	}
	
	public Responsavel(int codResponsavel, String nome, String cpf, String email, Usuario usuario, LocalDate dataNascimento, String miniCurriculo) {
		super(nome, cpf, email, usuario, dataNascimento);
		this.miniCurriculo = miniCurriculo;
		setCodResponsavel(codResponsavel);
	}

	public String getMiniCurriculo() {
		return miniCurriculo;
	}

	public int getCodResponsavel() {
		return codResponsavel;
	}

	public void setCodResponsavel(int codResponsavel) {
		if(codResponsavel < 0)
			throw new IllegalArgumentException("Erro: o campo codigo do responsavel nao pode estar vazio");
		else
			this.codResponsavel = codResponsavel;
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
