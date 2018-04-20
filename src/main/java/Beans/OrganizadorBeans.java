package Beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Model.Atividade;
import Model.EnumNivel;
import Model.Organizador;

public class OrganizadorBeans implements Beans{

	private int codOrganizador;
	private List<AtividadeBeans> atividadeRelacionadas;
	private EnumNivel nivel;

	public int getCodOrganizador() {
		return codOrganizador;
	}
	public void setCodOrganizador(int codOrganizador) {
		this.codOrganizador = codOrganizador;
	}
	public List<AtividadeBeans> getAtividadeRelacionadas() {
		return atividadeRelacionadas;
	}
	public void setAtividadeRelacionadas(List<AtividadeBeans> atividadeRelacionadas) {
		this.atividadeRelacionadas = atividadeRelacionadas;
	}
	public EnumNivel getNivel() {
		return nivel;
	}
	public void setNivel(EnumNivel nivel) {
		this.nivel = nivel;
	}

	@Override
	public Object toBusiness() {
		Organizador organizador = new Organizador();
		
		organizador.setCodOrganizador(this.getCodOrganizador());
		organizador.setNivel(this.getNivel());

		List<Atividade> atividadesRelacionadas = Collections.synchronizedList(new ArrayList<Atividade>());
		if (this.getAtividadeRelacionadas()!=null)
			for (int i=0; i<this.getAtividadeRelacionadas().size();i++)
				atividadesRelacionadas.add((Atividade) this.getAtividadeRelacionadas().get(i).toBusiness());
		organizador.setAtividadeRelacionadas(atividadesRelacionadas);
		
		return organizador;
	}

	@Override
	public Beans toBeans(Object object) {
		if(object == null)
			throw new IllegalArgumentException("O objeto a ser adicionado não é um Organizador!");
		if(!(object instanceof Organizador))
			throw new NullPointerException("O Organizador não pode ser nulo!");
		
		Organizador organizador = (Organizador) object;
		
		this.setCodOrganizador(organizador.getCodOrganizador());
		this.setNivel(organizador.getNivel());

		List<AtividadeBeans> atividadesRelacionadas = Collections.synchronizedList(new ArrayList<AtividadeBeans>());
		if (organizador.getAtividadeRelacionadas() != null)
			for (int i = 0; i < organizador.getAtividadeRelacionadas().size(); i++)
				atividadesRelacionadas.add((AtividadeBeans) new AtividadeBeans().toBeans(organizador.getAtividadeRelacionadas().get(i)));
		this.setAtividadeRelacionadas(atividadesRelacionadas);
		
		return this;
	}
}
