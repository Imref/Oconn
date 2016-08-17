/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Hibernate.NewHibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Imre
 */
public class EgyenlegAllitas {
    
    public void egyenlegetAllit(double egy, int szersz){
        
         Session s = NewHibernateUtil.getSessionFactory().openSession();
            String qv = "UPDATE Szerzodes SET egyenleg = " + egy + " WHERE szerszam = " + szersz + "";
            try {
                s.beginTransaction();
                s.createSQLQuery(qv).executeUpdate();
                s.getTransaction().commit();
                s.close();
            } catch (HibernateException erro) {
                s.getTransaction().rollback();
                s.close();
            }

        
    }
    
}
