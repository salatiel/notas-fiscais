package br.com.notasfiscais.dao;

import java.io.Serializable;

import br.com.notasfiscais.modelo.Produto;
import br.com.notasfiscais.util.JPAUtil;

public class ProdutoDao extends GenericDao<Produto, Serializable> {

    public ProdutoDao() {
	super(Produto.class, JPAUtil.getEntityManager());
    }
}