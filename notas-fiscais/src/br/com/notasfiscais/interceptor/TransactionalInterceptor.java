package br.com.notasfiscais.interceptor;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Interceptor
@Transactional
public class TransactionalInterceptor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
	manager.getTransaction().begin();
	Object resulted = context.proceed();
	manager.getTransaction().commit();
	return resulted;
    }
}
