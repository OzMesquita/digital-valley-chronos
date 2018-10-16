package br.ufc.russas.n2s.chronos.beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import br.ufc.russas.n2s.chronos.model.Apoio;
import br.ufc.russas.n2s.chronos.model.Atividade;
import br.ufc.russas.n2s.chronos.model.Colaborador;
import br.ufc.russas.n2s.chronos.model.EnumTipoAtividade;
import br.ufc.russas.n2s.chronos.model.EnumTipoPagamento;
import br.ufc.russas.n2s.chronos.model.InscricaoAtividade;
import br.ufc.russas.n2s.chronos.model.Organizador;
import br.ufc.russas.n2s.chronos.model.Realizacao;
import br.ufc.russas.n2s.chronos.model.Responsavel;

public class AtividadeBeans implements Beans {
	private long codAtividade;
	private List<AtividadeBeans> subAtividade;
	private String nome;
	private String descricao;
	private Atividade pai;
	private String sigla;
	private float totalHoras;
	private List<RealizacaoBeans> realizacao;
	private EnumTipoAtividade tipoAtividade;
	private String preRequisitos;
	private Responsavel responsavel;
	private int totalVagas;
	private int totalVagasComunidade;
	private String Local;
	private EnumTipoPagamento tipoPagamento;
	private List<ApoioBeans> apoiadores;
	private List<OrganizadorBeans> organizadores;
	private List<ColaboradorBeans> colaboradores;
	private List<InscricaoAtividadeBeans> participantes;
	private boolean divulgada;

	public long getCodAtividade() {
		return codAtividade;
	}

	public void setCodAtividade(long codAtividade) {
		this.codAtividade = codAtividade;
	}

	public List<AtividadeBeans> getSubAtividade() {
		return subAtividade;
	}

	public void setSubAtividade(List<AtividadeBeans> subAtividade) {
		this.subAtividade = subAtividade;
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
	
	public List<ColaboradorBeans> getColaboradores() {
		return colaboradores;
	}

	public void setColaboradores(List<ColaboradorBeans> colaboradores) {
		this.colaboradores = colaboradores;
	}

	public boolean isDivulgada() {
		return divulgada;
	}

	public void setDivulgada(boolean divulgada) {
		this.divulgada = divulgada;
	}

	@Override
	public Object toBusiness() {
		Atividade atividade = new Atividade();
		if (this.getCodAtividade() > 0) {
			atividade.setCodAtividade(this.getCodAtividade());
		}
		atividade.setNome(this.getNome());
		atividade.setDescricao(this.getDescricao());
		if (this.getPai() != null) {
			atividade.setPai(this.getPai());
		}
		atividade.setSigla(this.getSigla());
		if (this.getRealizacao() != null) {
			atividade.setTotalHoras();
		}
		atividade.setTipoAtividade(this.getTipoAtividade());
		if (this.getResponsavel() != null) {
			atividade.setResponsavel(this.getResponsavel());
		}
		atividade.setTotalVagas(this.getTotalVagas());
		atividade.setTotalVagasComunidade(this.getTotalVagasComunidade());
		if (this.getLocal() != null) {
			atividade.setLocal(this.getLocal());
		}
		if (this.getTipoPagamento() != null) {
			atividade.setTipoPagamento(this.getTipoPagamento());
		}
		atividade.setDivulgada(this.isDivulgada());
		List<Atividade> subAtividade = Collections.synchronizedList(new ArrayList<Atividade>());
		if (this.getSubAtividade() != null)
			for (int i = 0; i < this.getSubAtividade().size(); i++)
				subAtividade.add((Atividade) this.getSubAtividade().get(i).toBusiness());
		atividade.setSubAtividade(subAtividade);
		List<Realizacao> realizacao = new ArrayList<Realizacao>();
		if (this.getRealizacao() != null)
			for (int i = 0; i < this.getRealizacao().size(); i++)
				realizacao.add((Realizacao) this.getRealizacao().get(i).toBusiness());
		atividade.setRealizacao(realizacao);
		atividade.setPreRequisitos(this.preRequisitos);
		List<Apoio> apoiadores = Collections.synchronizedList(new ArrayList<Apoio>());
		if (this.getApoiadores() != null)
			for (int i = 0; i < this.getApoiadores().size(); i++)
				apoiadores.add((Apoio) this.getApoiadores().get(i).toBusiness());
		atividade.setApoiadores(apoiadores);
		List<Organizador> organizadores = Collections.synchronizedList(new ArrayList<Organizador>());
		if (this.getOrganizadores() != null)
			for (int i = 0; i < this.getOrganizadores().size(); i++)
				organizadores.add((Organizador) this.getOrganizadores().get(i).toBusiness());
		atividade.setOrganizadores(organizadores);
		List<Colaborador> colaboradores = Collections.synchronizedList(new ArrayList<Colaborador>());
		if (this.getColaboradores() != null)
			for (int i = 0; i < this.getColaboradores().size(); i++)
				colaboradores.add((Colaborador) this.getColaboradores().get(i).toBusiness());
		atividade.setColaboradores(colaboradores);
		List<InscricaoAtividade> participantes = Collections.synchronizedList(new ArrayList<InscricaoAtividade>());
		if (this.getParticipantes() != null)
			for (int i = 0; i < this.getParticipantes().size(); i++)
				participantes.add((InscricaoAtividade) this.getParticipantes().get(i).toBusiness());
		atividade.setParticipantes(participantes);
		
		return atividade;
	}

	@Override
	public Beans toBeans(Object object) {
		if (object == null)
			throw new NullPointerException("A Atividade não pode ser nula!");
		if (!(object instanceof Atividade))
			throw new IllegalArgumentException("O objeto a ser adicionado não é uma Atividade!");
		Atividade atividade = (Atividade) object;
		this.setCodAtividade(atividade.getCodAtividade());
		this.setNome(atividade.getNome());
		this.setDescricao(atividade.getDescricao());
		this.setPai(atividade.getPai());
		this.setSigla(atividade.getSigla());
		this.setTotalHoras(atividade.getTotalHoras());
		this.setTipoAtividade(atividade.getTipoAtividade());
		this.setResponsavel(atividade.getResponsavel());
		this.setTotalVagas(atividade.getTotalVagas());
		this.setTotalVagasComunidade(atividade.getTotalVagasComunidade());
		this.setLocal(atividade.getLocal());
		this.setTipoPagamento(atividade.getTipoPagamento());
		this.setPreRequisitos(atividade.getPreRequisitos());
		this.setDivulgada(atividade.isDivulgada());
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
		List<ApoioBeans> apoiadores = Collections.synchronizedList(new ArrayList<ApoioBeans>());
		if (atividade.getApoiadores() != null)
			for (int i = 0; i < atividade.getApoiadores().size(); i++)
				apoiadores.add((ApoioBeans) new ApoioBeans().toBeans(atividade.getApoiadores().get(i)));
		this.setApoiadores(apoiadores);
		List<OrganizadorBeans> organizadores = Collections.synchronizedList(new ArrayList<OrganizadorBeans>());
		if (atividade.getOrganizadores() != null)
			for (int i = 0; i < atividade.getOrganizadores().size(); i++)
				organizadores
						.add((OrganizadorBeans) new OrganizadorBeans().toBeans(atividade.getOrganizadores().get(i)));
		this.setOrganizadores(organizadores);
		List<ColaboradorBeans> colaboradores = Collections.synchronizedList(new ArrayList<ColaboradorBeans>());
		if (atividade.getColaboradores() != null)
			for (int i = 0; i < atividade.getColaboradores().size(); i++)
				colaboradores
						.add((ColaboradorBeans) new ColaboradorBeans().toBeans(atividade.getColaboradores().get(i)));
		this.setColaboradores(colaboradores);
		List<InscricaoAtividadeBeans> participantes = Collections.synchronizedList(new ArrayList<InscricaoAtividadeBeans>());
		if (atividade.getParticipantes() != null)
			for (int i = 0; i < atividade.getParticipantes().size(); i++)
				participantes
						.add((InscricaoAtividadeBeans) new InscricaoAtividadeBeans().toBeans(atividade.getParticipantes().get(i)));
		this.setParticipantes(participantes);
		return this;
	}

	public List<InscricaoAtividadeBeans> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<InscricaoAtividadeBeans> participantes) {
		this.participantes = participantes;
	}
}