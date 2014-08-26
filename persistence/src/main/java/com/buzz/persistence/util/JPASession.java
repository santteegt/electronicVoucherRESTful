package com.buzz.persistence.util;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.buzz.persistence.voucher.Ttipocontribuyente;
import java.util.List;

public class JPASession {
	
	public static void main(String []args)throws Exception {
		JPAController<Ttipocontribuyente> session = new JPAController();                
                Ttipocontribuyente tc; 
                
                //INSERT
              /*  tc = new Ttipocontribuyente();        
                tc.setDescripcion("Consumidor Final");
                session.create(tc);
                tc = new Ttipocontribuyente();        
                tc.setDescripcion("Cliente con RUC");
                session.create(tc);
                tc = new Ttipocontribuyente();        
                tc.setDescripcion("Cliente para borrar");
                session.create(tc);
                */
                 //UPDATE
               /* tc = new Ttipocontribuyente();        
                tc.setCtipocontribuyente(3);
                tc.setDescripcion("descripcion modificada");
                session.edit(tc);
                */
                //DELETE
              /*  tc = new Ttipocontribuyente();        
                tc.setCtipocontribuyente(3);
                session.destroy(tc);
                */
                //QUERY
                tc = new Ttipocontribuyente();        
                List<Ttipocontribuyente> lista = session.findAllEntities(tc);
                for(int i=0;i<lista.size();i++)
                {
                    System.out.println(lista.get(i).getCtipocontribuyente()+"  --  "+lista.get(i).getDescripcion());
                }
               
                System.exit(0);
	}

}
