package br.com.notasfiscais.managerbean;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.notasfiscais.dao.ProdutoDao;
import br.com.notasfiscais.modelo.Produto;

@ManagedBean
public class ProdutoBean {

    private Produto produto = new Produto();

    private List<Produto> produtos;

    private ProdutoDao dao = new ProdutoDao();
    
    private String nomeProduto;
    
    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Produto getProduto() {
	return produto;
    }

    public void setProduto(Produto produto) {
	this.produto = produto;
    }

    public void gravar() {

	dao.save(produto);

	this.produto = new Produto();
	this.produtos = dao.listAll();
    }

    public List<Produto> getProdutos() {
	if (produtos == null) {
	    this.produtos = dao.listAll();
	}
	return produtos;
    }

    public void remover(Produto produto) {
	dao.delete(produto.getId());
	this.produtos = dao.listAll();
    }

    public void buscar() {
	if (produto != null) {
	    nomeProduto = String.format("%%%s%%", nomeProduto);
	    this.produtos = dao.getListObjects(nomeProduto, "Produto.findByNome");
	    this.produto = new Produto();
	    nomeProduto = "";
	}
    }

    public void all() {
	this.produtos = dao.listAll();
    }
}
