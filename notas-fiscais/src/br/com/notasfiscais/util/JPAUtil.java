package br.com.notasfiscais.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

    private static final EntityManagerFactory emf = Persistence
	    .createEntityManagerFactory("notas");

    @Produces
    @RequestScoped
    public EntityManager getEntityManager() {
	return emf.createEntityManager();
    }

    public void destruir(@Disposes EntityManager em) {
	em.close();
    }
}