package br.com.notasfiscais.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class DaoFactory implements Serializable{

    private static final long serialVersionUID = 1L;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Produces
    public Dao createDao(InjectionPoint point) {
	ParameterizedType type = (ParameterizedType) point.getType();
	Class clazz = (Class) type.getActualTypeArguments()[0];
	return new Dao(clazz);
    }
}
