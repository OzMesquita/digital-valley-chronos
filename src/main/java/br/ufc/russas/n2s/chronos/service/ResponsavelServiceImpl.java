package br.ufc.russas.n2s.chronos.service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import br.ufc.russas.n2s.chronos.beans.ResponsavelBeans;
import br.ufc.russas.n2s.chronos.dao.ResponsavelDAOIfc;
import br.ufc.russas.n2s.chronos.model.Responsavel;
public class ResponsavelServiceImpl implements ResponsavelServiceIfc{
	private ResponsavelDAOIfc responsavelDAOIfc;
    public ResponsavelServiceImpl() {
    }
    public ResponsavelDAOIfc getResponsavelDAOIfc() {
        return responsavelDAOIfc;
    }
    @Autowired(required = true)
    public void setResponsavelDAOIfc(@Qualifier("responsavelDAOIfc") ResponsavelDAOIfc responsavelDAOIfc) {
        this.responsavelDAOIfc = responsavelDAOIfc;
    }
    @Override
    public void adicionaResponsavel(ResponsavelBeans responsavel) {
        this.responsavelDAOIfc.adicionaResponsavel((Responsavel) responsavel.toBusiness());
    }
    @Override
    public void atualizaResponsavel(ResponsavelBeans responsavel) {
        this.responsavelDAOIfc.atualizaResponsavel((Responsavel) responsavel.toBusiness());
    }
    @Override
    public void removeResponsavel(ResponsavelBeans responsavel) {
        this.responsavelDAOIfc.removeResponsavel((Responsavel) responsavel.toBusiness());
    }
    @Override
    public List<ResponsavelBeans> listaTodosResponsavels() {
        Responsavel responsavel = new Responsavel();
        List<ResponsavelBeans> responsavels = Collections.synchronizedList(new ArrayList<ResponsavelBeans>());
        List<Responsavel> resultados = this.responsavelDAOIfc.listaResponsavel(responsavel);
        for (Responsavel p : resultados)
            responsavels.add((ResponsavelBeans) new ResponsavelBeans().toBeans(p));
        return responsavels;
    }
    @Override
    public ResponsavelBeans getResponsavel(long codResponsavel) {
        Responsavel responsavel = new Responsavel();
        responsavel.setCodResponsavel(codResponsavel);
        return (ResponsavelBeans) new ResponsavelBeans().toBeans(this.responsavelDAOIfc.getResponsavel(responsavel));
    }
}