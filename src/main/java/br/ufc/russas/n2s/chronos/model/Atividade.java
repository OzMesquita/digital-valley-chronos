package br.ufc.russas.n2s.chronos.model;

import java.util.List;
import javax.persistence.CascadeType;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import br.ufc.russas.n2s.chronos.model.exceptions.IllegalCodeException;

@Entity
@Table(name = "atividade")
public class Atividade implements Comparable<Atividade> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codAtividade")
	private long codAtividade;
	@ManyToMany(targetEntity = Atividade.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name = "subatividades_atividade", joinColumns = {
			@JoinColumn(name = "atividade", referencedColumnName = "codAtividade") }, inverseJoinColumns = {
					@JoinColumn(name = "subAtividade", referencedColumnName = "codAtividade") })
	private List<Atividade> subAtividade;
	private String nome;
	private String descricao;
	@ManyToOne(targetEntity = Atividade.class)
	@JoinColumn(name = "pai", referencedColumnName = "codAtividade")
	private Atividade pai;
	private String sigla;
	private float totalHoras;
	@ManyToMany(targetEntity = Realizacao.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name = "realizacoes_atividade", joinColumns = {
			@JoinColumn(name = "atividade", referencedColumnName = "codAtividade") }, inverseJoinColumns = {
					@JoinColumn(name = "realizacao", referencedColumnName = "codRealizacao") })
	private List<Realizacao> realizacao;
	@Enumerated(EnumType.ORDINAL)
	private EnumTipoAtividade tipoAtividade;
	private String preRequisitos;
	@ManyToOne(targetEntity = Responsavel.class)
	@JoinColumn(name = "responsavel", referencedColumnName = "codResponsavel")
	private Responsavel responsavel;
	private int totalVagas;
	private int totalVagasComunidade;
	private String Local;
	@Enumerated(EnumType.ORDINAL)
	private EnumTipoPagamento tipoPagamento;
	private String localPagamento;
	@ManyToMany(targetEntity = Apoio.class, fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name = "apoiadores_atividade", joinColumns = {
			@JoinColumn(name = "atividade", referencedColumnName = "codAtividade") }, inverseJoinColumns = {
					@JoinColumn(name = "apoio", referencedColumnName = "codApoio") })
	private List<Apoio> apoiadores;
	@ManyToMany(targetEntity = Organizador.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name = "organizadores_atividade", joinColumns = {
			@JoinColumn(name = "atividade", referencedColumnName = "codAtividade") }, inverseJoinColumns = {
					@JoinColumn(name = "organizador", referencedColumnName = "codOrganizador") })
	private List<Organizador> organizadores;	
	@ManyToMany(targetEntity = Colaborador.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name = "colaboradores_atividade", joinColumns = {
			@JoinColumn(name = "atividade", referencedColumnName = "codAtividade") }, inverseJoinColumns = {
					@JoinColumn(name = "colaborador", referencedColumnName = "codColaborador") })
	private List<Colaborador> colaboradores;
	private boolean divulgada;
	@ManyToMany(targetEntity = UsuarioChronos.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name = "participantes_atividade", joinColumns = {
			@JoinColumn(name = "atividade", referencedColumnName = "codAtividade") }, inverseJoinColumns = {
					@JoinColumn(name = "participantes", referencedColumnName = "codUsuario") })
	private List<UsuarioChronos> participantes;

	// Construir primeiro o numero total de vagas de cada de atividade e depois o
	// total de vagas da comunidade.
	public Atividade() {
	}

	/**
	 * Atividade Raiz (Evento)
	 */
	public Atividade(String nome, String descricao, String sigla, List<Realizacao> realizacao,
			EnumTipoAtividade tipoAtividade, boolean campoAtivdade, String preRequisitos, Responsavel responsavel,
			int totalVagas, int totalVagasComunidade, String local, EnumTipoPagamento tipoPagamento,
			List<Apoio> apoiadores, List<Organizador> organizadores) {
		setNome(nome);
		setDescricao(descricao);
		setSigla(sigla);
		setRealizacao(realizacao);
		setTotalHoras();
		setTipoAtividade(tipoAtividade);
		setPreRequisitos(preRequisitos);
		setResponsavel(responsavel);
		setTotalVagas(totalVagas);
		setTotalVagasComunidade(totalVagasComunidade);
		setLocal(local);
		setTipoPagamento(tipoPagamento);
		setApoiadores(apoiadores);
		setOrganizadores(organizadores);
	}

	/**
	 * Demais Atividades
	 */
	public Atividade(String nome, String descricao, Atividade pai, String sigla, float totalHoras,
			List<Realizacao> realizacao, EnumTipoAtividade tipoAtividade, boolean campoAtivdade, String preRequisitos,
			Responsavel responsavel, int totalVagas, int totalVagasComunidade, String local,
			EnumTipoPagamento tipoPagamento, List<Apoio> apoiadores, List<Organizador> organizadores) {
		setNome(nome);
		setDescricao(descricao);
		setPai(pai);
		setSigla(sigla);
		setRealizacao(realizacao);
		setTotalHoras();
		setTipoAtividade(tipoAtividade);
		setPreRequisitos(preRequisitos);
		setResponsavel(responsavel);
		setTotalVagas(totalVagas);
		setTotalVagasComunidade(totalVagasComunidade);
		setLocal(local);
		setTipoPagamento(tipoPagamento);
		setApoiadores(apoiadores);
		setOrganizadores(organizadores);
	}

	public long getCodAtividade() {
		return codAtividade;
	}

	public void setCodAtividade(long codAtividade) {
		if (codAtividade > 0)
			this.codAtividade = codAtividade;
		else
			throw new IllegalCodeException("Código da atividade deve ser maior de zero!");
	}

	public List<Atividade> getSubAtividade() {
		return subAtividade;
	}

	public void setSubAtividade(List<Atividade> subAtividade) {
		this.subAtividade = subAtividade;
	}

	public void addSubAtividade(Atividade subAtividade) {
		this.subAtividade.add(subAtividade);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (!isEmpty(nome))
			this.nome = nome;
		else
			throw new IllegalArgumentException("Erro: o campo nome não pode estar vazio");
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		if (!isEmpty(descricao))
			this.descricao = descricao;
		else
			throw new IllegalArgumentException("Erro: o campo descricao não pode estar vazio");
	}

	public Atividade getPai() {
		return pai;
	}

	// Tratar automaticamente com o sistema!
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

	// Setar automaticamente esta variavel usando os atributos horaInicial e
	// horaFinal da classe "Realização", !
	public void setTotalHoras() {
		int minutos = 0;
		if (realizacao != null) {
			for (int i = 0; i < realizacao.size(); i++) {
				totalHoras += realizacao.get(i).getHoraFinal().getHour() - realizacao.get(i).getHoraInicio().getHour();
				minutos += realizacao.get(i).getHoraFinal().getMinute() - realizacao.get(i).getHoraInicio().getMinute();
			}
		}
		totalHoras += (int) (minutos / 60) + ((minutos % 60) / 100.0);
	}

	public List<Realizacao> getRealizacao() {
		return realizacao;
	}

	public void setRealizacao(List<Realizacao> realizacao) {
		this.realizacao = realizacao;
	}

	public void addRealizacao(Realizacao realizacao) {
		if (realizacao == null)
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

	public String getPreRequisitos() {
		return preRequisitos;
	}

	public void setPreRequisitos(String preRequisitos) {
		this.preRequisitos = preRequisitos;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		if (responsavel == null)
			throw new IllegalArgumentException("Erro: a atividade precisa de um responsavel.");
		else
			this.responsavel = responsavel;
	}

	public int getTotalVagas() {
		return totalVagas;
	}

	public void setTotalVagas(int totalVagas) {
		if (totalVagas < 0)
			throw new IllegalArgumentException("Erro: o campo total de vagas nao pode ser negativa.");
		else
			this.totalVagas = totalVagas;
	}

	public int getTotalVagasComunidade() {
		return totalVagasComunidade;
	}

	public void setTotalVagasComunidade(int totalVagasComunidade) {
		if (totalVagasComunidade < 0)
			throw new IllegalArgumentException("Erro: o campo total de vagas da comunidade nao pode ser negativa.");
		else if (totalVagasComunidade > getTotalVagas())
			throw new IllegalArgumentException(
					"Erro: o campo total de vagas da comunidade nao pode ser maior que o total de vagas.");
		else
			this.totalVagasComunidade = totalVagasComunidade;
	}

	public String getLocal() {
		return Local;
	}

	public void setLocal(String local) {
		if (!isEmpty(local))
			Local = local;
		else
			throw new IllegalArgumentException("Erro: o campo local não pode estar vazio.");
	}

	public EnumTipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(EnumTipoPagamento tipoPagamento) {
		if (tipoPagamento == null)
			throw new IllegalArgumentException("Erro: o campo pagamento não pode estar vazio.");
		else
			this.tipoPagamento = tipoPagamento;
	}

	public String getLocalPagamento() {
		return localPagamento;
	}

	public void setLocalPagamento(String localPagamento) {
		this.localPagamento = localPagamento;

	}

	public List<Apoio> getApoiadores() {
		return apoiadores;
	}

	public void setApoiadores(List<Apoio> apoiadores) {
		this.apoiadores = apoiadores;
	}

	public void addApoaidor(Apoio apoiador) {
		if (apoiador == null)
			throw new IllegalArgumentException("Erro: o campo apoiador não pode ser nulo.");
		else
			apoiadores.add(apoiador);
	}

	public List<Organizador> getOrganizadores() {
		return organizadores;
	}

	public void setOrganizadores(List<Organizador> organizadores) {
		this.organizadores = organizadores;
	}

	public void addOrganizador(Organizador organizador) {
		if (organizador == null)
			throw new IllegalArgumentException("Erro: o campo organizador não pode ser nulo.");
		else
			organizadores.add(organizador);
	}
	
	public List<Colaborador> getColaboradores() {
		return colaboradores;
	}

	public void setColaboradores(List<Colaborador> colaboradores) {
		this.colaboradores = colaboradores;
	}
	
	public List<UsuarioChronos> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<UsuarioChronos> participantes) {
		this.participantes = participantes;
	}

	public void setTotalHoras(float totalHoras) {
		this.totalHoras = totalHoras;
	}

	
	public void addColaborador(Colaborador colaborador) {
		if (colaborador == null)
			throw new IllegalArgumentException("Erro: o campo colaborador não pode ser nulo.");
		else
			colaboradores.add(colaborador);
	}

	public Atividade adicionaAtividade() {
		return this;
	}

	public Atividade atualizaAtividade() {
		return this;
	}

	public boolean isDivulgada() {
		return divulgada;
	}

	public void setDivulgada(boolean divulgada) {
		this.divulgada = divulgada;
	}

	@Override
	public int compareTo(Atividade o) {
		return this.getNome().compareTo(o.getNome());
	}

	public static boolean isEmpty(String string) {
		if (string == null || string.equals(""))
			return true;
		return false;
	}
}