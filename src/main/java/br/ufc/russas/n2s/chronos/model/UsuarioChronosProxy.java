/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.chronos.model;

/**
 *
 * @author N2S
 */
public class UsuarioChronosProxy extends UsuarioChronos{
    private UsuarioChronos usuario;
    
    public UsuarioChronosProxy(UsuarioChronos usuario) {
        setUsuario(usuario);
    }

    public UsuarioChronos getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioChronos usuario) {
        if (usuario != null) {
            this.usuario = usuario;
        } else {
            throw new NullPointerException("Usuário do Chronos não pode ser vazio!");
        }
    }
    
    @Override
    public UsuarioChronos adicionaNivel(UsuarioChronos usuario, EnumPermissao permissao) throws IllegalAccessException{
        if (getUsuario().getPermissoes().contains(EnumPermissao.ADMINISTRADOR)) {
            return super.adicionaNivel(usuario, permissao);
        } else {
            throw new IllegalAccessException("Você não é um administrador do Chronos!");
        }
    }
    
    @Override
    public UsuarioChronos removeNivel(UsuarioChronos usuario, EnumPermissao permissao) throws IllegalAccessException{
        if (getUsuario().getPermissoes().contains(EnumPermissao.ADMINISTRADOR)) {
            return super.removeNivel(usuario, permissao);
        } else {
            throw new IllegalAccessException("Você não é um administrador do Chronos!");
        }
    }
}
