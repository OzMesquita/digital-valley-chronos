/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.chronos.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufc.russas.n2s.chronos.beans.AtividadeBeans;
import br.ufc.russas.n2s.chronos.beans.RealizacaoBeans;
import br.ufc.russas.n2s.chronos.beans.UsuarioBeans;
import br.ufc.russas.n2s.chronos.model.Atividade;
import br.ufc.russas.n2s.chronos.model.EnumEstadoAtividade;
import br.ufc.russas.n2s.chronos.model.EnumTipoAtividade;
import br.ufc.russas.n2s.chronos.service.AtividadeServiceIfc;

@Controller("indexController")
@RequestMapping("/")
public class IndexController {

	private static final EnumEstadoAtividade ABERTA = null;
	private static final EnumEstadoAtividade ANDAMENTO = null;
	private static final EnumEstadoAtividade FINALIZADA = null;
	private AtividadeServiceIfc atividadeServiceIfc;

	public AtividadeServiceIfc getAtividadeServiceIfc() {
		return atividadeServiceIfc;
	}

	@Autowired(required = true)
	public void setAtividadeServiceIfc(@Qualifier("atividadeServiceIfc") AtividadeServiceIfc atividadeServiceIfc) {
		this.atividadeServiceIfc = atividadeServiceIfc;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getIndex(Model model) {
		Atividade atividade = new Atividade();
		atividade.setDivulgada(true);
		List<AtividadeBeans> atividades = this.getAtividadeServiceIfc().listaAtividadesOrfans(atividade);
		List<AtividadeBeans> novasatividades = new ArrayList<>();
		for (AtividadeBeans atividadebeans : atividades) {				
			 	
			for (RealizacaoBeans realizacao : atividadebeans.getRealizacao()) {				
				// mant�m a data mais recente na posi��o 0 para que possa ser exibida na pagina
				// inicio.jsp para cada atividade
				LocalDateTime termino = realizacao.getHoraFinal(); 				
				if(!termino.isBefore(LocalDateTime.now())){
					novasatividades.add(atividadebeans);					
				}
				break;
			}
		}
		model.addAttribute("categoria", "In�cio");
		model.addAttribute("estado", "in�cio");
		model.addAttribute("atividades", novasatividades);
		return "inicio";
	}

	@RequestMapping(value = "/cadastrarSubAtividades", method = RequestMethod.GET)
	public String getCadastroSub(Model model, HttpServletRequest request) {
		return "cadastrar-atividade";
	}

	@RequestMapping(value = "/{categoria}", method = RequestMethod.GET)
	public String getCategoria(Model model, @PathVariable String categoria) {
		Atividade atividade = new Atividade();
		atividade.setTipoAtividade(EnumTipoAtividade.valorEnumPeloNome(categoria.toUpperCase()));
		List<AtividadeBeans> atividades = this.getAtividadeServiceIfc().listaAtividadesOrfans(atividade);
		model.addAttribute("categoria", "In�cio");
		model.addAttribute("estado", "in�cio");
		model.addAttribute("atividades", atividades);
		return "inicio";
	}
	
	@RequestMapping(value = "/estado/{estado}", method = RequestMethod.GET)
    public String getEstados(Model model, @PathVariable String estado){
        Atividade atividade = new Atividade();
        atividade.setDivulgada(true);
        EnumEstadoAtividade e = null;
        List<AtividadeBeans> listaDeAtividades = this.getAtividadeServiceIfc().listaAtividadesOrfans(atividade);   
        List<AtividadeBeans> novasatividades = new ArrayList<>();
        
        if (estado.equals("aberta")){   	// ATIVIDADES ABERTAS
        	for (AtividadeBeans atividadebeans : listaDeAtividades ) {
        		if(atividadebeans.getRealizacao()!=null) {
        			for(RealizacaoBeans realizacao : atividadebeans.getRealizacao()) {
        				LocalDateTime inicio = realizacao.getHoraInicio();
        				LocalDateTime termino = realizacao.getHoraFinal();
                		if(inicio.isAfter(LocalDateTime.now()) && termino.isAfter(LocalDateTime.now()) ) {
                			novasatividades.add(atividadebeans);
                			break;
                		}
        			}
        			
        		}
        		
        	}
        	
            e = ABERTA;
            model.addAttribute("categoria", "Atividade abertas");
            model.addAttribute("estado", e);            
            System.out.println("Entrou em abertas");
            
            
        } else if( estado.equals("andamento")) {  // ATIVIDADES EM ANDAMENTO
        	for (AtividadeBeans atividadebeans : listaDeAtividades ) {
        		if(atividadebeans.getRealizacao()!=null) {
        			for(RealizacaoBeans realizacao : atividadebeans.getRealizacao()) {
        				LocalDateTime termino = realizacao.getHoraFinal();
                    	LocalDateTime inicio = realizacao.getHoraInicio();
                		if(termino.isAfter(LocalDateTime.now()) && inicio.isBefore(LocalDateTime.now()) || inicio.isEqual(LocalDateTime.now())) {
                			novasatividades.add(atividadebeans);
                			break;
                		}
        			}
        			
        		}
        		
        	}
        	
            e = ANDAMENTO;
            model.addAttribute("categoria", "Atividades em andamento");
            model.addAttribute("estado", e);
            
            System.out.println("Entrou em Adamento");
            
            
        } else if (estado.equals("finalizada")) { // ATIVIDADES FINALIZADAS
        	for (AtividadeBeans atividadebeans : listaDeAtividades ) {
        		if(atividadebeans.getRealizacao()!=null) {
        			for(RealizacaoBeans realizacao : atividadebeans.getRealizacao()) {
        				LocalDateTime termino = realizacao.getHoraFinal();    		
            			if(termino.isBefore(LocalDateTime.now())){ // data de termino da atividade � antes da data atual
            				novasatividades.add(atividadebeans);
            				System.out.println("FINALIZADA: Entrou no if / Termino"+termino);    
            				break;
            		    }
        			}
        			
        		}
        		
        	}
        	
            e = FINALIZADA;
            model.addAttribute("categoria", "Atividades finalizadas");
            model.addAttribute("estado", e);
            
            System.out.println("Entrou em finalizada");
        } 
        //atividade.setEstado(e);
        //AtividadeBeans atividadebeans = new AtividadeBeans();
        model.addAttribute("atividades", novasatividades);
        
        
        
        return "inicio";
	}
        
 }


	

