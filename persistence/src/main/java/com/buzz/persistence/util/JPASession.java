package com.buzz.persistence.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.buzz.persistence.voucher.Tcontribuyente;
import com.buzz.persistence.voucher.TcontribuyentePK;
import com.buzz.persistence.voucher.Tusuarioid;

public class JPASession {
	
	private static final String PERSISTENCE_UNIT_NAME = "persistence";
	
	private EntityManagerFactory factory = null;
	
	private EntityManager em = null;
	
	private static JPASession instance = null; 

	
	public static JPASession getSession()throws Exception {
		if(instance == null) {
			instance = new JPASession();	
		} 
		instance.beginTransaction();
		return instance;
	}
	
	public void beginTransaction()throws Exception {
		if(! em.getTransaction().isActive())
			em.getTransaction().begin();
			
	}
	
	public void commitTransaction()throws Exception {
		if(em.isOpen() && em.getTransaction().isActive()){
			em.getTransaction().commit();
			em.close();
		}
	}
	
	
	public <T> T getEntity(Class<T> type, Object pk)throws Exception {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(type);
		criteriaQuery.from(type);
		TypedQuery query = em.createQuery(criteriaQuery);
		return (T)query.getSingleResult();

		
		
	}
	public void persistEntity(Object obj)throws Exception {
		em.persist(obj);
	}

	
	private JPASession() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		
		
	}
	
	public static void main(String []args)throws Exception {
		JPASession session = JPASession.getSession();
		TcontribuyentePK pk = new TcontribuyentePK();
		pk.setCcontribuyente(1);
		pk.setFhasta(
				new Timestamp(
				new SimpleDateFormat("dd-MM-yyyy").parse("31-12-2999").getTime()
				));
		Tcontribuyente c = session.getEntity(Tcontribuyente.class, pk);
		System.out.println(c);
		
		
	}

}
