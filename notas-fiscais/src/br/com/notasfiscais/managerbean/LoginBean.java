package br.com.notasfiscais.managerbean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.notasfiscais.dao.UsuarioDao;
import br.com.notasfiscais.modelo.Usuario;
import br.com.notasfiscais.util.UsuarioLogado;

@Named
@RequestScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private UsuarioLogado usuarioLogado;

    @Inject
    private UsuarioDao dao;

    private Usuario usuario = new Usuario();

    private List<Usuario> usuarios = null;

    public Usuario getUsuario() {
	return usuario;
    }

    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
	return usuarios;
    }

    public String efetuaLogin() {
	String url = "login";

	if (dao.existe(usuario)) {
	    this.usuarioLogado.setUsuario(usuario);
	    url = "produto?faces-redirect=true";
	}

	return url;
    }

    public String logout() {
	this.usuarioLogado.setUsuario(null); 
	return "login?faces-redirect=true";
    }
}
