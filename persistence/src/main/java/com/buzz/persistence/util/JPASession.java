package com.buzz.persistence.util;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.buzz.persistence.voucher.Ttipocontribuyente;
import com.buzz.persistence.voucher.Tusuario;

import java.util.ArrayList;
import java.util.List;

public class JPASession {
	
	private static ThreadLocal<EntityManagerFactory> threadFactory = new ThreadLocal<EntityManagerFactory>();
	
	private static ThreadLocal<EntityManager> threadEntityManager = new ThreadLocal<EntityManager>();
	
	private static ThreadLocal<EntityTransaction> threadEntityTransaction = new ThreadLocal<EntityTransaction>();
	
	public static void getManager()throws Exception {
		EntityManagerFactory factory = JPASession.threadFactory.get();
		EntityManager entityManager = JPASession.threadEntityManager.get();
		if(factory == null) {
			factory = Persistence.createEntityManagerFactory("persistence");
			JPASession.threadFactory.set(factory);
			entityManager = factory.createEntityManager();
			JPASession.threadEntityManager.set(entityManager);
		}
	}
	
	public static void beginTransaction()throws Exception {
		JPASession.getManager();
		if(JPASession.threadEntityTransaction.get() == null ||
				!JPASession.threadEntityTransaction.get().isActive()) {
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
	
	public static void closeManager()throws Exception {
		if(JPASession.threadFactory.get() != null) {
			JPASession.threadEntityManager.get().close();
			JPASession.threadFactory.get().close();
		}
	}
	
	public static <T> List<T> getResultSetQuery(String queryString)throws Exception {
		List <T> resultList = null;
		try {
			if(JPASession.threadFactory.get() != null) {
				EntityManager entityManager = JPASession.threadEntityManager.get();
				//TypedQuery<Tusuario> queryL = entityManager.createQuery("from Tusuario a", Tusuario.class);
				Query queryl= entityManager.createQuery(queryString);
				resultList = queryl.getResultList();
				
				
			}
		}catch(Exception e) {
			JPASession.threadEntityTransaction.get().rollback();
			JPASession.closeManager();
			throw e;
		}
		return resultList;
	}
	
	
	
	public static void main(String []args)throws Exception {
		
		JPASession.beginTransaction();
		Ttipocontribuyente ttipocontr = new Ttipocontribuyente(2);
		ttipocontr.setDescripcion("TEST4");
		EntityManager em = JPASession.getEntityManager();
		ttipocontr = em.merge(ttipocontr);
		//em.persist(ttipocontr);
		JPASession.commitTransaction(false);
		List<Tusuario> list = JPASession.getResultSetQuery("from Tusuario a");
		for(Tusuario entity: list){
			System.out.println(entity.toString());
		}
		JPASession.commitTransaction(true);
	}

}
