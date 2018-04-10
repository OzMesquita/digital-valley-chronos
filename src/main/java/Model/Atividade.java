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

	private EnumTipoAtividade tipoAtividade;

	private boolean campoAtivdade;

	private ArrayList<Atividade> preRequisitos;

	private Responsavel responsavel;

	private int totalVagas;

	private int totalVagasComunidade;

	private String Local;

	private EnumTipoPagamento tipoPagamento;

	private ArrayList<Apoio> apoiadores;

	private ArrayList<Organizador> organizadores;

	//Construir primeiro o numero total de vagas de cada de atividade e depois o total de vagas da comunidade.
	public Atividade() {
		
	}	
	
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
		if(!isEmpty(nome))
			this.nome = nome;
		else
			throw new IllegalArgumentException("Erro: o campo nome não pode estar vazio");
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		if(!isEmpty(descricao))
			this.descricao = descricao;
		else
			throw new IllegalArgumentException("Erro: o campo descricao não pode estar vazio");
	}

	public Atividade getPai() {
		return pai;
	}
	
	//Tratar automaticamente com o sistema!
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
	
	//Setar automaticamente esta variavel usando os atributos horaInicial e horaFinal da classe "Realização", !
	public void setTotalHoras(float totalHoras) {
		this.totalHoras = totalHoras;
	}

	public ArrayList<Realizacao> getRealizacao() {
		return realizacao;
	}

	public void setRealizacao(ArrayList<Realizacao> realizacao) {
		this.realizacao = realizacao;
	}

	public EnumTipoAtividade getTipoAtividade() {
		return tipoAtividade;
	}

	public void setTipoAtividade(EnumTipoAtividade tipoAtividade) {
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

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		if(responsavel == null)
			throw new IllegalArgumentException("Erro: a atividade precisa de um responsavel.");
		else
			this.responsavel = responsavel;
	}

	public int getTotalVagas() {
		return totalVagas;
	}

	public void setTotalVagas(int totalVagas) {
		if(totalVagas < 0) 
			throw new IllegalArgumentException("Erro: o campo total de vagas nao pode ser negativa.");
		else
			this.totalVagas = totalVagas;
	}

	public int getTotalVagasComunidade() {
		return totalVagasComunidade;
	}
	
	
	public void setTotalVagasComunidade(int totalVagasComunidade) {
		if(totalVagasComunidade < 0) 
			throw new IllegalArgumentException("Erro: o campo total de vagas da comunidade nao pode ser negativa.");
		else if(totalVagasComunidade >= getTotalVagas())
			throw new IllegalArgumentException("Erro: o campo total de vagas da comunidade nao pode ser maior que o total de vagas.");
		else
			this.totalVagasComunidade = totalVagasComunidade;
	}

	public String getLocal() {
		return Local;
	}

	public void setLocal(String local) {
		if(!isEmpty(local))
			Local = local;
		else
			throw new IllegalArgumentException("Erro: o campo local não pode estar vazio.");
	}

	public EnumTipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(EnumTipoPagamento tipoPagamento) {
		if(tipoPagamento == null)
			throw new IllegalArgumentException("Erro: o campo local não pode estar vazio.");
		else
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
	
	private boolean isEmpty(String string) {
		if(string==null||string.equals(""))
			return false;
		return true;
	}
}
