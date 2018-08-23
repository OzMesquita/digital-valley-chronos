/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.chronos.dao;

import br.ufc.russas.n2s.chronos.model.UsuarioChronos;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author Wallison Carlos
 */
public class UsuarioDAOImpl implements UsuarioDAOIfc {
	private DAOIfc<UsuarioChronos> daoImpl;

	@Autowired
	public void setDAOIfc(@Qualifier("daoImpl") DAOIfc<UsuarioChronos> dao) {
		this.daoImpl = dao;
	}

	@Override
	public UsuarioChronos adicionaUsuario(UsuarioChronos usuario) {
		return this.daoImpl.adiciona(usuario);
	}

	@Override
	public UsuarioChronos atualizaUsuario(UsuarioChronos usuario) {
		return this.daoImpl.atualiza(usuario);
	}

	@Override
	public void removeUsuario(UsuarioChronos usuario) {
		this.daoImpl.remove(usuario);
	}

	@Override
	public List<UsuarioChronos> listaUsuarios(UsuarioChronos usuario) {
		return this.daoImpl.lista(usuario);
	}

	@Override
	public UsuarioChronos getUsuario(UsuarioChronos usuario) {
		return this.daoImpl.getObject(usuario, usuario.getCodUsuario());
	}

	@Override
	public UsuarioChronos getUsuarioControleDeAcesso(UsuarioChronos usuario) {
		Session session = this.daoImpl.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		try {
			Example example = Example.create(usuario);
			usuario = (UsuarioChronos) session.createCriteria(UsuarioChronos.class).add(example).uniqueResult();
			t.commit();
			return usuario;
		} catch (RuntimeException e) {
			t.rollback();
			throw e;
		} finally {
			session.close();
		}
	}
}