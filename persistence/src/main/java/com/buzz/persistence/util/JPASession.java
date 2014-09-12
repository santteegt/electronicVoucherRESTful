package com.buzz.persistence.util;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.buzz.persistence.voucher.Ttipocontribuyente;
import com.buzz.persistence.voucher.Tusuario;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JPASession {
	
	//private static ThreadLocal<EntityManagerFactory> threadFactory = new ThreadLocal<EntityManagerFactory>();
	private static EntityManagerFactory factory;
	
	private static ThreadLocal<EntityManager> threadEntityManager = new ThreadLocal<EntityManager>();
	
	private static ThreadLocal<EntityTransaction> threadEntityTransaction = new ThreadLocal<EntityTransaction>();
	
	public static void getManager()throws Exception {
		//EntityManagerFactory factory = JPASession.threadFactory.get();
		EntityManager entityManager = JPASession.threadEntityManager.get();
		if(JPASession.factory == null) {
			JPASession.factory = Persistence.createEntityManagerFactory("persistence");
			//JPASession.threadFactory.set(factory);
			//entityManager = factory.createEntityManager();
			//JPASession.threadEntityManager.set(entityManager);
		}
	}
	
	public synchronized static void beginTransaction()throws Exception {
		JPASession.getManager();
		if(JPASession.threadEntityManager.get() == null ||
				!JPASession.threadEntityManager.get().isOpen()) {
			JPASession.threadEntityManager.set(JPASession.factory.createEntityManager());
			EntityTransaction transaction = JPASession.threadEntityManager.get().getTransaction();
			JPASession.threadEntityTransaction.set(transaction);
			transaction.begin();
		}
				
	}
	
	private static EntityTransaction getEntityTransacion()throws Exception {
		return JPASession.threadEntityTransaction.get();
	}
	
	public static EntityManager getEntityManager()throws Exception {
		return JPASession.threadEntityManager.get();
	}
	
	public static void commitTransaction(Boolean closeManager)throws Exception {
		EntityTransaction transaction = JPASession.threadEntityTransaction.get();
		if(transaction.isActive()) {
			transaction.commit();
		}
		if(closeManager) {
			JPASession.closeManager();
		}
		
	}
	
	public static void rollbackTransaction()throws Exception {
		EntityTransaction transaction = JPASession.threadEntityTransaction.get();
		if(transaction.isActive()) {
			transaction.rollback();
		}
		JPASession.closeManager();
		
	}
	
	public static void closeManager()throws Exception {
		//if(JPASession.threadFactory.get() != null) {
		if(JPASession.threadEntityManager.get() != null) {
			JPASession.threadEntityManager.get().close();
			JPASession.threadEntityManager.set(null);
			//JPASession.threadFactory.get().close();
		}
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
		//em.merge(pBean);
		em.persist(pBean);
	}
	
	
	public static void main(String []args)throws Exception {
		
		JPASession.beginTransaction();
		Ttipocontribuyente ttipocontr = new Ttipocontribuyente(2);
		ttipocontr.setDescripcion("TEST2");
		JPASession.saveOrUpdate(ttipocontr);
		//em.persist(ttipocontr);
		JPASession.commitTransaction(false);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user", "ADMIN");
		/*Tusuarioid user = (Tusuarioid)JPASession.getQueryBean("from Tusuarioid a where cusuario=:user", params);
		System.out.println(user.toString());*/
		List<Tusuario> list = JPASession.getResultSetQuery("from Tusuario a"); 
		for(Tusuario entity: list){
			System.out.println(entity.toString());
		}
		JPASession.commitTransaction(true);
	}

}
