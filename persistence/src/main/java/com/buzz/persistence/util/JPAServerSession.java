package com.buzz.persistence.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.service.spi.InjectService;

public class JPAServerSession {
	
	@PersistenceContext(unitName="persistence")
	private static EntityManager em;
	
	private static ThreadLocal<EntityManager> threadEntityManager = new ThreadLocal<EntityManager>();
	
	private static ThreadLocal<EntityTransaction> threadEntityTransaction = new ThreadLocal<EntityTransaction>();
	
	
	public static void beginTransaction()throws Exception {
		JPAServerSession.threadEntityManager.set(em);
		EntityTransaction transaction = JPAServerSession.threadEntityManager.get().getTransaction();
		JPAServerSession.threadEntityTransaction.set(transaction);
		transaction.begin();
		
				
	}
	
	public static void commitTransaction(Boolean closeManager)throws Exception {
		EntityTransaction transaction = JPAServerSession.threadEntityTransaction.get();
		if(transaction.isActive()) {
			transaction.commit();
		}
		if(closeManager) {
			JPASession.closeManager();
		}
		
	}
	
	public static void closeManager()throws Exception {
		JPAServerSession.threadEntityManager.get().close();
	}
	
	public static <T> List<T> getResultSetQuery(String queryString, Map<String,Object>... params)throws Exception {
		Map<String,Object> parameters = params != null?params[0]:new HashMap<String, Object>();
		List <T> resultList = null;
		try {
			if(JPASession.getEntityManager() != null) {
				EntityManager entityManager = JPASession.getEntityManager();
				Query queryl= entityManager.createQuery(queryString);
				setParameters(queryl, parameters);
				resultList = queryl.getResultList();

			} else {
				throw new NullPointerException("Transacción no inicializada");
			}
		}catch(Exception e) {
			//JPASession.threadEntityTransaction.get().rollback();
			//JPASession.closeManager();
			throw e;
		}
		return resultList;
	}
	
	public static Object getQueryBean(String queryString, Map<String,Object>... params)throws Exception {
		Map<String,Object> parameters = params != null?params[0]:new HashMap<String, Object>();
		Object bean = null;
		if(JPASession.getEntityManager() != null) {
			EntityManager entityManager = JPASession.getEntityManager();
			Query queryl= entityManager.createQuery(queryString);
			setParameters(queryl, parameters);
			bean = queryl.getSingleResult();

		} else {
			throw new NullPointerException("Transacción no inicializada");
		}
		return bean;
	}
	
	private static void setParameters(Query pQuery, Map<String,Object> params)throws Exception {
		for(String paramName:params.keySet()) {
			pQuery.setParameter(paramName, params.get(paramName));
		}
	}
	
	public static void saveOrUpdate(Object pBean)throws Exception{
		EntityManager em = JPASession.getEntityManager();
		if(em == null) {
			throw new NullPointerException("Transacción no inicializada");
		}
		em.merge(pBean);
	}

}
