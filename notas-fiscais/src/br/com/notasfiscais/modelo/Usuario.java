package br.com.notasfiscais.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
	@NamedQuery(name = "Usuario.findByLogin", query = "SELECT u FROM Usuario u WHERE u.login like :campo") })
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String senha;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getLogin() {
	return login;
    }

    public void setLogin(String login) {
	this.login = login;
    }

    public String getSenha() {
	return senha;
    }

    public void setSenha(String senha) {
	this.senha = senha;
    }

}
