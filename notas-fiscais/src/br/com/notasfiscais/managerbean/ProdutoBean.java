package br.com.notasfiscais.managerbean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.notasfiscais.dao.ProdutoDao;
import br.com.notasfiscais.modelo.Produto;

@Named
@RequestScoped
public class ProdutoBean {

    @Inject
    private ProdutoDao dao;

    private Produto produto = new Produto();

    private List<Produto> produtos;

    private String nome;

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public Produto getProduto() {
	return produto;
    }

    public void setProduto(Produto produto) {
	this.produto = produto;
    }

    public void cancelar() {
	this.produto = new Produto();
    }

    public void gravar() {
	if (produto.getId() == null) {
	    dao.adiciona(produto);

	} else {
	    dao.atualiza(produto);
	}

	this.produto = new Produto();
	this.produtos = dao.listaTodos();
    }

    public List<Produto> getProdutos() {
	if (produtos == null) {
	    this.produtos = dao.listaTodos();
	}
	return produtos;
    }

    public void remover(Produto produto) {
	dao.remove(produto);
	this.produtos = dao.listaTodos();
    }

    public void buscar() {
	if (nome != null && nome.trim().length() > 0) {
	    this.nome = String.format("%%%s%%", nome);
	    this.produtos = dao.buscaPorNome(nome);
	    this.nome = "";
	}
    }

    public void all() {
	this.produto = new Produto();
	this.produtos = dao.listaTodos();
    }
}
