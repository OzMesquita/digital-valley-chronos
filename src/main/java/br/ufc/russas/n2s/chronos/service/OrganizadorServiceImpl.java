package br.ufc.russas.n2s.chronos.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import br.ufc.russas.n2s.chronos.beans.OrganizadorBeans;
import br.ufc.russas.n2s.chronos.dao.OrganizadorDAOIfc;
import br.ufc.russas.n2s.chronos.model.Organizador;

public class OrganizadorServiceImpl implements OrganizadorServiceIfc{
	private OrganizadorDAOIfc organizadorDAOIfc;

    public OrganizadorServiceImpl() {
    }

    public OrganizadorDAOIfc getOrganizadorDAOIfc() {
        return organizadorDAOIfc;
    }

    @Autowired(required = true)
    public void setOrganizadorDAOIfc(@Qualifier("organizadorDAOIfc") OrganizadorDAOIfc organizadorDAOIfc) {
        this.organizadorDAOIfc = organizadorDAOIfc;
    }

    @Override
    public void adicionaOrganizador(OrganizadorBeans organizador) {
        this.organizadorDAOIfc.adicionaOrganizador((Organizador) organizador.toBusiness());
    }

    @Override
    public void atualizaOrganizador(OrganizadorBeans organizador) {
        this.organizadorDAOIfc.atualizaOrganizador((Organizador) organizador.toBusiness());
    }

    @Override
    public void removeOrganizador(OrganizadorBeans organizador) {
        this.organizadorDAOIfc.removeOrganizador((Organizador) organizador.toBusiness());
    }

    @Override
    public List<OrganizadorBeans> listaTodosOrganizadores() {
        Organizador organizador = new Organizador();
        List<OrganizadorBeans> organizadors = Collections.synchronizedList(new ArrayList<OrganizadorBeans>());
        List<Organizador> resultados = this.organizadorDAOIfc.listaOrganizador(organizador);
        for (Organizador p : resultados)
            organizadors.add((OrganizadorBeans) new OrganizadorBeans().toBeans(p));
        return organizadors;
    }

    @Override
    public OrganizadorBeans getOrganizador(long codOrganizador) {
        Organizador organizador = new Organizador();
        organizador.setCodOrganizador(codOrganizador);
        return (OrganizadorBeans) new OrganizadorBeans().toBeans(this.organizadorDAOIfc.getOrganizador(organizador));
    }
}
