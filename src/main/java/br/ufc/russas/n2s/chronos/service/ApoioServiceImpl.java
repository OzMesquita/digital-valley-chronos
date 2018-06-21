package br.ufc.russas.n2s.chronos.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufc.russas.n2s.chronos.beans.ApoioBeans;
import br.ufc.russas.n2s.chronos.dao.ApoioDAOIfc;
import br.ufc.russas.n2s.chronos.model.Apoio;
@Service("apoioServiceIfc")
@Transactional
public class ApoioServiceImpl implements ApoioServiceIfc{
	private ApoioDAOIfc apoioDAOIfc;

    public ApoioServiceImpl() {
    }

    public ApoioDAOIfc getApoioDAOIfc() {
        return apoioDAOIfc;
    }

    @Autowired(required = true)
    public void setApoioDAOIfc(@Qualifier("apoioDAOIfc") ApoioDAOIfc apoioDAOIfc) {
        this.apoioDAOIfc = apoioDAOIfc;
    }

    @Override
    public void adicionaApoio(ApoioBeans apoio) {
        this.apoioDAOIfc.adicionaApoio((Apoio) apoio.toBusiness());
    }

    @Override
    public void atualizaApoio(ApoioBeans apoio) {
        this.apoioDAOIfc.atualizaApoio((Apoio) apoio.toBusiness());
    }

    @Override
    public void removeApoio(ApoioBeans apoio) {
        this.apoioDAOIfc.removeApoio((Apoio) apoio.toBusiness());
    }

    @Override
    public List<ApoioBeans> listaTodosApoios() {
        Apoio apoio = new Apoio();
        List<ApoioBeans> apoios = Collections.synchronizedList(new ArrayList<ApoioBeans>());
        List<Apoio> resultados = this.apoioDAOIfc.listaApoio(apoio);
        for (Apoio p : resultados)
            apoios.add((ApoioBeans) new ApoioBeans().toBeans(p));
        return apoios;
    }

    @Override
    public ApoioBeans getApoio(long codApoio) {
        Apoio apoio = new Apoio();
        apoio.setCodApoio(codApoio);
        return (ApoioBeans) new ApoioBeans().toBeans(this.apoioDAOIfc.getApoio(apoio));
    }
}
