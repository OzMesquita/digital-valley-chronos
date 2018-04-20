package Beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import Model.Apoio;
import Model.Atividade;
import Model.EnumTipoAtividade;
import Model.EnumTipoPagamento;
import Model.Organizador;
import Model.Realizacao;
import Model.Responsavel;

public class AtividadeBeans implements Beans {

	private int codAtividade;

	private List<AtividadeBeans> subAtividade;

	private int ID;

	private String nome;

	private String descricao;

	private Atividade pai;

	private String sigla;

	private float totalHoras;

	private List<RealizacaoBeans> realizacao;

	private EnumTipoAtividade tipoAtividade;

	private boolean campoAtivdade;

	private List<AtividadeBeans> preRequisitos;

	private Responsavel responsavel;

	private int totalVagas;

	private int totalVagasComunidade;

	private String Local;

	private EnumTipoPagamento tipoPagamento;

	private List<ApoioBeans> apoiadores;

	private List<OrganizadorBeans> organizadores;

	public int getCodAtividade() {
		return codAtividade;
	}

	public void setCodAtividade(int codAtividade) {
		this.codAtividade = codAtividade;
	}

	public List<AtividadeBeans> getSubAtividade() {
		return subAtividade;
	}

	public void setSubAtividade(List<AtividadeBeans> subAtividade) {
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
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public List<RealizacaoBeans> getRealizacao() {
		return realizacao;
	}

	public void setRealizacao(List<RealizacaoBeans> realizacao) {
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

	public List<AtividadeBeans> getPreRequisitos() {
		return preRequisitos;
	}

	public void setPreRequisitos(List<AtividadeBeans> preRequisitos) {
		this.preRequisitos = preRequisitos;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
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
		Local = local;
	}

	public EnumTipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(EnumTipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public List<ApoioBeans> getApoiadores() {
		return apoiadores;
	}

	public void setApoiadores(List<ApoioBeans> apoiadores) {
		this.apoiadores = apoiadores;
	}

	public List<OrganizadorBeans> getOrganizadores() {
		return organizadores;
	}

	public void setOrganizadores(List<OrganizadorBeans> organizadores) {
		this.organizadores = organizadores;
	}

	@Override
	public Object toBusiness() {
		Atividade atividade = new Atividade();
		
		atividade.setCodAtividade(this.getCodAtividade());
		atividade.setCodAtividade(this.getCodAtividade());
		atividade.setNome(this.getNome());
		atividade.setDescricao(this.getDescricao());
		atividade.setPai(this.getPai());
		atividade.setSigla(this.getSigla());
		atividade.setTotalHoras();
		atividade.setTipoAtividade(this.getTipoAtividade());
		atividade.setCampoAtivdade(this.isCampoAtivdade());
		atividade.setResponsavel(this.getResponsavel());
		atividade.setTotalVagas(this.getTotalVagas());
		atividade.setTotalVagasComunidade(this.getTotalVagasComunidade());
		atividade.setLocal(this.getLocal());
		atividade.setTipoPagamento(this.getTipoPagamento());

		List<Atividade> subAtividade = Collections.synchronizedList(new ArrayList<Atividade>());
		if (this.getSubAtividade()!=null)
			for (int i=0; i<this.getSubAtividade().size();i++)
				subAtividade.add((Atividade) this.getSubAtividade().get(i).toBusiness());
		atividade.setSubAtividade(subAtividade);

		List<Realizacao> realizacao = Collections.synchronizedList(new ArrayList<Realizacao>());
		if (this.getRealizacao()!=null)
			for (int i=0; i<this.getRealizacao().size();i++)
				realizacao.add((Realizacao) this.getRealizacao().get(i).toBusiness());
		atividade.setRealizacao(realizacao);

		List<Atividade> preRequisitos = Collections.synchronizedList(new ArrayList<Atividade>());
		if (this.getPreRequisitos()!=null)
			for (int i=0; i<this.getPreRequisitos().size();i++)
				preRequisitos.add((Atividade) this.getPreRequisitos().get(i).toBusiness());
		atividade.setPreRequisitos(preRequisitos);

		List<Apoio> apoiadores = Collections.synchronizedList(new ArrayList<Apoio>());
		if (this.getApoiadores()!=null)
			for (int i=0; i<this.getApoiadores().size();i++)
				apoiadores.add((Apoio) this.getApoiadores().get(i).toBusiness());
		atividade.setApoiadores(apoiadores);

		List<Organizador> organizadores = Collections.synchronizedList(new ArrayList<Organizador>());
		if (this.getOrganizadores()!=null)
			for (int i=0; i<this.getOrganizadores().size();i++)
				organizadores.add((Organizador) this.getOrganizadores().get(i).toBusiness());
		atividade.setOrganizadores(organizadores);

		return atividade;
	}

	@Override
	public Beans toBeans(Object object) {
		if(object == null)
			throw new NullPointerException("A Atividade não pode ser nula!");
		if(!(object instanceof Atividade))
			throw new IllegalArgumentException("O objeto a ser adicionado não é uma Atividade!");
		
		Atividade atividade = (Atividade) object;
		
		this.setCodAtividade(atividade.getCodAtividade());
		this.setNome(atividade.getNome());
		this.setDescricao(atividade.getDescricao());
		this.setPai(atividade.getPai());
		this.setSigla(atividade.getSigla());
		this.setTotalHoras(atividade.getTotalHoras());
		this.setTipoAtividade(atividade.getTipoAtividade());
		this.setCampoAtivdade(atividade.isCampoAtivdade());
		this.setResponsavel(atividade.getResponsavel());
		this.setTotalVagas(atividade.getTotalVagas());
		this.setTotalVagasComunidade(atividade.getTotalVagasComunidade());
		this.setLocal(atividade.getLocal());
		this.setTipoPagamento(atividade.getTipoPagamento());
	
		List<AtividadeBeans> subAtividade = Collections.synchronizedList(new ArrayList<AtividadeBeans>());
		if (atividade.getSubAtividade() != null)
			for (int i = 0; i < atividade.getSubAtividade().size(); i++)
				subAtividade.add((AtividadeBeans) new AtividadeBeans().toBeans(atividade.getSubAtividade().get(i)));
		this.setSubAtividade(subAtividade);
	
		List<RealizacaoBeans> realizacao = Collections.synchronizedList(new ArrayList<RealizacaoBeans>());
		if (atividade.getRealizacao() != null)
			for (int i = 0; i < atividade.getRealizacao().size(); i++)
				realizacao.add((RealizacaoBeans) new RealizacaoBeans().toBeans(atividade.getRealizacao().get(i)));
		this.setRealizacao(realizacao);				
	
		List<AtividadeBeans> preRequisitos = Collections.synchronizedList(new ArrayList<AtividadeBeans>());
		if (atividade.getPreRequisitos() != null)
			for (int i = 0; i < atividade.getPreRequisitos().size(); i++)
				preRequisitos.add((AtividadeBeans) new AtividadeBeans().toBeans(atividade.getPreRequisitos().get(i)));
		this.setPreRequisitos(preRequisitos);				
	
		List<ApoioBeans> apoiadores = Collections.synchronizedList(new ArrayList<ApoioBeans>());
		if (atividade.getApoiadores() != null)
			for (int i = 0; i < atividade.getApoiadores().size(); i++)
				apoiadores.add((ApoioBeans) new ApoioBeans().toBeans(atividade.getApoiadores().get(i)));
		this.setApoiadores(apoiadores);				
	
		List<OrganizadorBeans> organizadores = Collections.synchronizedList(new ArrayList<OrganizadorBeans>());
		if (atividade.getOrganizadores() != null)
			for (int i = 0; i < atividade.getOrganizadores().size(); i++)
				organizadores.add((OrganizadorBeans) new OrganizadorBeans().toBeans(atividade.getOrganizadores().get(i)));
		this.setOrganizadores(organizadores);
		
		return this;
	}
}
