package br.com.notasfiscais.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.notasfiscais.modelo.Produto;

public class ProdutoDao extends Dao<Produto> implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public ProdutoDao() {
	super(Produto.class);
    }

    @Inject
    private EntityManager manager;

    public List<Produto> buscaPorNome(String nome) {

	String jpql = "select p from Produto p where "
		+ " lower(p.nome) like :nome order by p.nome";

	List<Produto> lista = manager.createQuery(jpql, Produto.class)
		.setParameter("nome", nome + "%").getResultList();

	return lista;
    }
}