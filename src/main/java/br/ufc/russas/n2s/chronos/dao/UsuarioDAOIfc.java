/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.chronos.dao;

import br.ufc.russas.n2s.chronos.model.UsuarioChronos;
import java.util.List;

/**
 *
 * @author Wallison Carlos
 */
public interface UsuarioDAOIfc {
	/**
	 * Método resposável por fazer a persistência de uma etapa.
	 * 
	 * @param etapa
	 */
	UsuarioChronos adicionaUsuario(UsuarioChronos usuario);

	/**
	 * Método resposável por fazer a atualização de uma etapa.
	 * 
	 * @param etapa
	 */
	UsuarioChronos atualizaUsuario(UsuarioChronos usuario);

	/**
	 * Método resposável por fazer a remoção de uma etapa.
	 * 
	 * @param etapa
	 */
	void removeUsuario(UsuarioChronos usuario);

	/**
	 * Método resposável por fazer a listagem de todas as etapas.
	 * 
	 * @return List<Etapa>
	 */
	List<UsuarioChronos> listaUsuarios(UsuarioChronos usuario);

	/**
	 * Método resposável por pegar do banco de dados uma etapa a partir do código
	 * informado.
	 * 
	 * @param codEtapa
	 * @return Etapa
	 */
	UsuarioChronos getUsuario(UsuarioChronos usuario);

	/**
	 * Método resposável por pegar do banco de dados uma etapa a partir do código
	 * informado.
	 * 
	 * @param codEtapa
	 * @return Etapa
	 */
	UsuarioChronos getUsuarioControleDeAcesso(UsuarioChronos usuario);
}