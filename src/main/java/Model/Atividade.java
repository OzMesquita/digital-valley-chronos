package Model;

import java.util.ArrayList;

import model.Pessoa;

public class Atividade {

	private ArrayList<Atividade> subAtividade;

	private int ID;

	private String nome;

	private String descricao;

	private Atividade pai;

	private String sigla;

	private float totalHoras;

	private ArrayList<Realizacao> realizacao;

	private EnumTipo tipoAtividade;

	private boolean campoAtivdade;

	private ArrayList<Atividade> preRequisitos;

	private Palestrante Palestrante;

	private int totalVagas;

	private int totalVagasComunidade;

	private String Local;

	private int tipoPagamento;

	private ArrayList<Apoio> apoiadores;

	private ArrayList<Organizador> organizadores;

	public ArrayList<Atividade> getSubAtividade() {
		return subAtividade;
	}

	public void setSubAtividade(ArrayList<Atividade> subAtividade) {
		this.subAtividade = subAtividade;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if(!vazio(nome))
			this.nome = nome;
		else
			throw new IllegalArgumentException("Erro: o campo nome não pode estar vazio");
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		if(!vazio(descricao))
			this.descricao = descricao;
		else
			throw new IllegalArgumentException("Erro: o campo descricao não pode estar vazio");
	}

	public Atividade getPai() {
		return pai;
	}

	public void setPai(Atividade pai) {
		this.pai = pai;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public float getTotalHoras() {
		return totalHoras;
	}

	public void setTotalHoras(float totalHoras) {
		this.totalHoras = totalHoras;
	}

	public ArrayList<Realizacao> getRealizacao() {
		return realizacao;
	}

	public void setRealizacao(ArrayList<Realizacao> realizacao) {
		this.realizacao = realizacao;
	}

	public EnumTipo getTipoAtividade() {
		return tipoAtividade;
	}

	public void setTipoAtividade(EnumTipo tipoAtividade) {
		this.tipoAtividade = tipoAtividade;
	}

	public boolean isCampoAtivdade() {
		return campoAtivdade;
	}

	public void setCampoAtivdade(boolean campoAtivdade) {
		this.campoAtivdade = campoAtivdade;
	}

	public ArrayList<Atividade> getPreRequisitos() {
		return preRequisitos;
	}

	public void setPreRequisitos(ArrayList<Atividade> preRequisitos) {
		this.preRequisitos = preRequisitos;
	}

	public Palestrante getPalestrante() {
		return Palestrante;
	}

	public void setPalestrante(Palestrante palestrante) {
		Palestrante = palestrante;
	}

	public int getTotalVagas() {
		return totalVagas;
	}

	public void setTotalVagas(int totalVagas) {
		this.totalVagas = totalVagas;
	}

	public int getTotalVagasComunidade() {
		return totalVagasComunidade;
	}

	public void setTotalVagasComunidade(int totalVagasComunidade) {
		this.totalVagasComunidade = totalVagasComunidade;
	}

	public String getLocal() {
		return Local;
	}

	public void setLocal(String local) {
		if(!vazio(local))
			Local = local;
		else
			throw new IllegalArgumentException("Erro: o campo local não pode estar vazio");
	}

	public int getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(int tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public ArrayList<Apoio> getApoiadores() {
		return apoiadores;
	}

	public void setApoiadores(ArrayList<Apoio> apoiadores) {
		this.apoiadores = apoiadores;
	}

	public ArrayList<Organizador> getOrganizadores() {
		return organizadores;
	}

	public void setOrganizadores(ArrayList<Organizador> organizadores) {
		this.organizadores = organizadores;
	}
	
	private boolean vazio(String string) {
		if(string==null||string.equals(""))
			return false;
		return true;
	}
}
