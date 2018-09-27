package br.ufc.russas.n2s.chronos.beans;

import br.ufc.russas.n2s.chronos.model.Colaborador;

public class ColaboradorBeans implements Beans {
	private long CodColaborador;
	private String nome;
	private String funcao;
	
	public long getCodColaborador() {
		return CodColaborador;
	}
	public void setCodColaborador(long CodColaborador) {
		this.CodColaborador = CodColaborador;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getFuncao() {
		return funcao;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	@Override
	public Object toBusiness() {
		Colaborador colaborador = new Colaborador();
		if(this.CodColaborador>0) {
			colaborador.setCodColaborador(CodColaborador);
		}
		colaborador.setFuncao(this.getFuncao());
		colaborador.setNome(this.getNome());
		return colaborador;
	}
	@Override
	public Beans toBeans(Object object) {
		if(object==null)
			throw new NullPointerException("O Organizador não pode ser nulo!");
		if (!(object instanceof Colaborador))
			throw new IllegalArgumentException("O objeto a ser adicionado não é um Colaborador!");
		Colaborador colaborador = (Colaborador) object;
		if(colaborador.getCodColaborador()>0)
			this.setCodColaborador(colaborador.getCodColaborador());
		this.setFuncao(colaborador.getFuncao());
		this.setNome(colaborador.getNome());
		return this;
	}
}
