package Model;

import java.time.LocalDate;

import model.Pessoa;
import model.Usuario;

public class Palestrante extends Pessoa{
	
	private String miniCurriculo;

	public Palestrante(String nome, String cpf, String email, Usuario usuario, LocalDate dataNascimento, String miniCurriculo) {
		super(nome, cpf, email, usuario, dataNascimento);
		this.miniCurriculo = miniCurriculo;
	}

	public String getMiniCurriculo() {
		return miniCurriculo;
	}

	public void setMiniCurriculo(String miniCurriculo) {
		this.miniCurriculo = miniCurriculo;
	}
}
