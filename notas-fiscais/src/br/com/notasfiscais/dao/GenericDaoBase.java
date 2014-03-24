package br.com.notasfiscais.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Salatiel
 * @param <T>
 * @param <PK>
 */
public interface GenericDaoBase<T, PK extends Serializable> {

    @SuppressWarnings("rawtypes")
    public Class getObjectClass();

    public T save(T object);

    public T load(PK primaryKey);

    public T get(PK primaryKey);

    public List<T> listAll();

    public T update(T object);

    public boolean delete(PK primaryKey);

}
