package Model;

import java.time.LocalDate;

import Facade.Facade;
import model.Pessoa;
import model.Usuario;

public class Responsavel extends Pessoa implements Comparable<Pessoa>{
	
	private String miniCurriculo;

	public Responsavel(String nome, String cpf, String email, Usuario usuario, LocalDate dataNascimento, String miniCurriculo) {
		super(nome, cpf, email, usuario, dataNascimento);
		this.miniCurriculo = miniCurriculo;
	}

	public String getMiniCurriculo() {
		return miniCurriculo;
	}

	public void setMiniCurriculo(String miniCurriculo) {
		if(!Facade.isEmpty(miniCurriculo))
			throw new IllegalArgumentException("Erro: o campo local n�o pode estar vazio.");
		else
			this.miniCurriculo = miniCurriculo;
	}

	@Override
	public int compareTo(Pessoa o) {
		return this.getNome().compareTo(o.getNome());
	}
}