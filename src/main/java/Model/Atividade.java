package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import Facade.Facade;
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

	/**Atividade Raiz (Evento)
	 * */
	public Atividade(int iD, String nome, String descricao, String sigla,
			ArrayList<Realizacao> realizacao, EnumTipoAtividade tipoAtividade, boolean campoAtivdade,
			ArrayList<Atividade> preRequisitos, Responsavel responsavel, int totalVagas, int totalVagasComunidade,
			String local, EnumTipoPagamento tipoPagamento, ArrayList<Apoio> apoiadores,
			ArrayList<Organizador> organizadores) {
		setID(iD);
		setNome(nome);
		setDescricao(descricao);
		setSigla(sigla);
		setRealizacao(realizacao);
		setTotalHoras();
		setTipoAtividade(tipoAtividade);
		setCampoAtivdade(campoAtivdade);
		setPreRequisitos(preRequisitos);
		setResponsavel(responsavel);
		setTotalVagas(totalVagas);
		setTotalVagasComunidade(totalVagasComunidade);
		setLocal(local);
		setTipoPagamento(tipoPagamento);
		setApoiadores(apoiadores);
		setOrganizadores(organizadores);
	}
	
	/**Demais Atividades
	 * */
	public Atividade(int iD, String nome, String descricao, Atividade pai, String sigla, float totalHoras,
			ArrayList<Realizacao> realizacao, EnumTipoAtividade tipoAtividade, boolean campoAtivdade,
			ArrayList<Atividade> preRequisitos, Responsavel responsavel, int totalVagas, int totalVagasComunidade,
			String local, EnumTipoPagamento tipoPagamento, ArrayList<Apoio> apoiadores,
			ArrayList<Organizador> organizadores) {
		setID(iD);
		setNome(nome);
		setDescricao(descricao);
		setPai(pai);
		setSigla(sigla);
		setRealizacao(realizacao);
		setTotalHoras();
		setTipoAtividade(tipoAtividade);
		setCampoAtivdade(campoAtivdade);
		setPreRequisitos(preRequisitos);
		setResponsavel(responsavel);
		setTotalVagas(totalVagas);
		setTotalVagasComunidade(totalVagasComunidade);
		setLocal(local);
		setTipoPagamento(tipoPagamento);
		setApoiadores(apoiadores);
		setOrganizadores(organizadores);
	}

	public ArrayList<Atividade> getSubAtividade() {
		return subAtividade;
	}

	public void setSubAtividade(ArrayList<Atividade> subAtividade) {
		this.subAtividade = subAtividade;
	}
	
	public void addSubAtividade(Atividade subAtividade) {
		this.subAtividade.add(subAtividade);
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
		if(!Facade.isEmpty(nome))
			this.nome = nome;
		else
			throw new IllegalArgumentException("Erro: o campo nome não pode estar vazio");
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		if(!Facade.isEmpty(descricao))
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
	public void setTotalHoras() {
		totalHoras = ( realizacao.get(realizacao.size()-1).getHoraFinal().getHour() ) - ( realizacao.get(0).getHoraInicio().getHour() );
	}
	
	public ArrayList<Realizacao> getRealizacao() {
		return realizacao;
	}

	public void setRealizacao(ArrayList<Realizacao> realizacao) {
		this.realizacao = realizacao;
	}
	
	public void addRealizacao(Realizacao realizacao) {
		if(realizacao == null)
			throw new IllegalArgumentException("Erro: o campo realizacao não pode ser nulo.");
		else
			this.realizacao.add(realizacao);
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
	
	public void addPreRequisito(Atividade preRequisito) {
		preRequisitos.add(preRequisito);
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
		else if(totalVagasComunidade > getTotalVagas())
			throw new IllegalArgumentException("Erro: o campo total de vagas da comunidade nao pode ser maior que o total de vagas.");
		else
			this.totalVagasComunidade = totalVagasComunidade;
	}

	public String getLocal() {
		return Local;
	}

	public void setLocal(String local) {
		if(!Facade.isEmpty(local))
			Local = local;
		else
			throw new IllegalArgumentException("Erro: o campo local não pode estar vazio.");
	}

	public EnumTipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(EnumTipoPagamento tipoPagamento) {
		if(tipoPagamento == null)
			throw new IllegalArgumentException("Erro: o campo pagamento não pode estar vazio.");
		else
			this.tipoPagamento = tipoPagamento;
	}

	public ArrayList<Apoio> getApoiadores() {
		return apoiadores;
	}

	public void setApoiadores(ArrayList<Apoio> apoiadores) {
		this.apoiadores = apoiadores;
	}
	
	public void addApoaidor(Apoio apoiador) {
		if(apoiador == null)
			throw new IllegalArgumentException("Erro: o campo apoiador não pode ser nulo.");
		else
			apoiadores.add(apoiador);
	}
	public ArrayList<Organizador> getOrganizadores() {
		return organizadores;
	}

	public void setOrganizadores(ArrayList<Organizador> organizadores) {
		this.organizadores = organizadores;
	}
	
	public void addOrganizador(Organizador organizador) {
		if(organizador == null)
			throw new IllegalArgumentException("Erro: o campo organizador não pode ser nulo.");
		else
			organizadores.add(organizador);
	}
}
