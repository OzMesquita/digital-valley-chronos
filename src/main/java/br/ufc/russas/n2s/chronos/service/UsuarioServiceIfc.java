/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.chronos.service;

import br.ufc.russas.n2s.chronos.beans.UsuarioBeans;
import br.ufc.russas.n2s.chronos.model.EnumPermissao;
import java.util.List;

/**
 *
 * @author N2S
 */
public interface UsuarioServiceIfc extends ServiceIfc{
    UsuarioBeans adicionaUsuario(UsuarioBeans usuario);
    UsuarioBeans atualizaUsuario(UsuarioBeans usuario);
    void removeUsuario(UsuarioBeans usuario);
    List<UsuarioBeans> listaTodosUsuarios();
    UsuarioBeans getUsuario(long codUsuario, long codUsuarioControleDeAcesso);
    UsuarioBeans getUsuarioControleDeAcesso(long codUsuarioControleDeAcesso);
    void adicionaNivel(UsuarioBeans usuario, EnumPermissao permissao) throws IllegalAccessException;
    void removeNivel(UsuarioBeans usuario, EnumPermissao permissao) throws IllegalAccessException;
    List<UsuarioBeans> listaAvaliadores();
}
