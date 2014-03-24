package br.com.notasfiscais.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.notasfiscais.modelo.Usuario;
import br.com.notasfiscais.util.JPAUtil;

public class UsuarioDao extends GenericDao<Usuario, Serializable> {

    public UsuarioDao() {
	super(Usuario.class, JPAUtil.getEntityManager());
    }

    public boolean existe(Usuario usuario) {

	EntityManager manager = JPAUtil.getEntityManager();

	Query query = manager
		.createQuery(
			"select u from Usuario u where u.login = :pLogin and u.senha = :pSenha")
		.setParameter("pLogin", usuario.getLogin())
		.setParameter("pSenha", usuario.getSenha());

	boolean encontrado = !query.getResultList().isEmpty();

	return encontrado;
    }
}