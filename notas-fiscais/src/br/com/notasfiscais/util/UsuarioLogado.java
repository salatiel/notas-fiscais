package br.com.notasfiscais.util;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.notasfiscais.modelo.Usuario;

@Named
@SessionScoped
public class UsuarioLogado implements Serializable {

    private static final long serialVersionUID = 1L;

    private Usuario usuario;

    public Usuario getUsuario() {
	return usuario;
    }

    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
    }

    public boolean isLogado() {
	return this.usuario != null;
    }
}
