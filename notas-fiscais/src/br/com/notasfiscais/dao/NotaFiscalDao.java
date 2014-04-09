package br.com.notasfiscais.dao;

import java.io.Serializable;

import br.com.notasfiscais.modelo.NotaFiscal;

public class NotaFiscalDao extends Dao<NotaFiscal> implements Serializable {

    private static final long serialVersionUID = 1L;

    public NotaFiscalDao() {
	super(NotaFiscal.class);
    }
}
