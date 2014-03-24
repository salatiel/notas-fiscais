package br.com.notasfiscais.dao;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * 
 * @author Salatiel
 * @param <T>
 * @param <PK>
 */
public class GenericDao<T, PK extends Serializable> implements
	GenericDaoBase<T, PK> {

    private EntityManager manager = null;

    @SuppressWarnings("rawtypes")
    private final Class objectClass;

    /**
     * Constructor default.
     * 
     * @param objectClass
     *            class for use
     */
    @SuppressWarnings("rawtypes")
    public GenericDao(final Class objectClass, EntityManager manager) {

	this.manager = manager;

	this.objectClass = objectClass;
    }

    public EntityManager getEntityManager() {
	return manager;
    }

    public void beginTransaction() {

	if (manager != null && manager.isOpen()) {

	    manager.getTransaction().begin();
	}
    }

    public void refresh(T object) {

	manager.refresh(object);
    }

    /**
     * 
     * @param object
     *            , A classe que será persistobjecta no banco de dados
     * @return True se conseguir persistir, do contrario false
     */
    @Override
    public T save(T object) {

	try {

	    manager = getEntityManager();

	    beginTransaction();

	    manager.persist(object);

	    manager.getTransaction().commit();
	} catch (Exception e) {
	    e.printStackTrace();
	    manager.getTransaction().rollback();

	    Logger.getLogger(objectClass.getName()).log(Level.SEVERE,
		    e.getMessage(), e);
	}

	return object;
    }

    @Override
    public T update(T object) {

	try {

	    manager = getEntityManager();

	    beginTransaction();

	    object = manager.merge(object);

	    manager.getTransaction().commit();
	} catch (Exception e) {

	    e.printStackTrace();

	    manager.getTransaction().rollback();

	    Logger.getLogger(objectClass.getName()).log(Level.SEVERE,
		    e.getMessage(), e);
	}

	return object;
    }

    @Override
    public boolean delete(PK primaryKey) {

	boolean deleted = false;

	try {
	    manager = getEntityManager();

	    beginTransaction();

	    T object = get(primaryKey);

	    manager.remove(object);

	    manager.getTransaction().commit();

	    deleted = true;
	} catch (Exception e) {
	    e.printStackTrace();
	    manager.getTransaction().rollback();

	    Logger.getLogger(objectClass.getName()).log(Level.SEVERE,
		    e.getMessage(), e);
	}

	return deleted;
    }

    /**
     * 
     * @return Uma lista de objetos retornado pelo BD
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> listAll() {

	List<T> objects = null;

	try {

	    manager = getEntityManager();

	    String nameQuery = String.format("%s.findAll",
		    objectClass.getSimpleName());

	    Query query = manager.createNamedQuery(nameQuery, objectClass);

	    objects = (List<T>) query.getResultList();
	} catch (Exception e) {
	    e.printStackTrace();
	    System.out.println("Erro List " + e.getMessage());
	    manager.getTransaction().rollback();

	    Logger.getLogger(objectClass.getName()).log(Level.SEVERE,
		    e.getMessage(), e);
	}

	return objects;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T load(final PK primaryKey) {

	T object = null;

	try {

	    manager = getEntityManager();

	    beginTransaction();

	    object = (T) manager.find(objectClass, primaryKey);
	} catch (Exception e) {
	    // TODO use log correct
	    e.printStackTrace();
	    manager.getTransaction().rollback();

	    Logger.getLogger(objectClass.getName()).log(Level.SEVERE,
		    e.getMessage(), e);
	}

	return object;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Class getObjectClass() {

	return objectClass;
    }

    @Override
    public T get(PK primaryKey) {

	return load(primaryKey);
    }

    /**
     * 
     * @param campo
     *            o campo de referência para a consulta, deve ser do mesmo tipo
     *            da coluna do banco de dados
     * @param nomeConsulta
     *            o nome da consulta que será realizada
     * @return Uma lista de objetos retornada pela consulta no banco de dados
     */
    @SuppressWarnings("unchecked")
    public List<T> getListObjects(Object campo, String nomeConsulta) {

	List<T> objetos = null;

	try {

	    manager = getEntityManager();

	    Query query = manager.createNamedQuery(nomeConsulta);

	    query.setParameter("campo", campo);

	    objetos = (List<T>) query.getResultList();
	} catch (Exception e) {
	    e.printStackTrace();
	    manager.getTransaction().rollback();

	    Logger.getLogger(objectClass.getName()).log(Level.SEVERE,
		    e.getMessage(), e);
	}

	return objetos;
    }

    /**
     * 
     * @param campoId
     *            o objectentificador da tabela, deve ser do mesmo tipo que esta
     *            na tabela
     * @param campo
     *            campo o campo de referência para a consulta, deve ser do
     *            mesmo tipo da coluna do banco de dados
     * @param nomeConsulta
     *            o nome da consulta que será realizada
     * @return Object - Um objeto retornado pela consulta no banco de dados
     */
    @SuppressWarnings("unchecked")
    public List<T> getOneObject(String nomeConsulta, Object campo, Object campo1) {

	List<T> objs = null;

	try {

	    manager = getEntityManager();

	    Query query = manager.createNamedQuery(nomeConsulta);

	    query.setParameter("campo", campo);

	    query.setParameter("campo1", campo1);

	    objs = (List<T>) query.getResultList();
	} catch (Exception e) {
	    e.printStackTrace();
	    manager.getTransaction().rollback();

	    Logger.getLogger(objectClass.getName()).log(Level.SEVERE,
		    e.getMessage(), e);
	}

	return objs;
    }

    /**
     * 
     * @param campo
     *            O campo que será pesquisado no banco, deve ser do mesmo tipo
     *            da coluna do banco
     * @param nomeConsulta
     *            O nome da consulta que será realizada no banco
     * @return Um objeto da banco
     */
    @SuppressWarnings("unchecked")
    public T getObject(Object campo, String nomeConsulta) {

	T object = null;

	try {

	    manager = getEntityManager();

	    beginTransaction();

	    Query query = manager.createNamedQuery(nomeConsulta);

	    query.setParameter("campo", campo);

	    Object obj = query.getSingleResult();

	    if (obj != null) {

		object = (T) obj;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    manager.getTransaction().rollback();

	    Logger.getLogger(objectClass.getName()).log(Level.SEVERE,
		    e.getMessage(), e);
	}

	return object;
    }
}
