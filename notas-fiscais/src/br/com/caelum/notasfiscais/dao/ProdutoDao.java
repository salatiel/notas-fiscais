package br.com.caelum.notasfiscais.dao;

import java.io.Serializable;

import br.com.caelum.notasfiscais.modelo.Produto;
import br.com.caelum.notasfiscais.util.JPAUtil;

public class ProdutoDao extends GenericDao<Produto, Serializable> {

    public ProdutoDao() {
	super(Produto.class, JPAUtil.getEntityManager());
    }
}