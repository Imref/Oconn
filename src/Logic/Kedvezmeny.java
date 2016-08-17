/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Data.Szerzodes;
import Hibernate.NewHibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Imre
 */
public class Kedvezmeny {
    
    private final double KEDVEZMENY = 0.15; 
    
    
    public void kedvezmenyBeallit(int szerzodesszam){
        
        
         Session stb = NewHibernateUtil.getSessionFactory().openSession();
        Query qtab = stb.createQuery("from Szerzodes szt Where szerszam = "+ szerzodesszam +" ");
        List sztList = qtab.list(); 
        stb.close();
        
        Szerzodes sz = (Szerzodes)sztList.get(0);
            
        if (sz.getInternet()!= 0 && sz.getTelefon()!=0 && sz.getTv()!=0) {
            
             Session s = NewHibernateUtil.getSessionFactory().openSession();
            String qv = "UPDATE Szerzodes SET  kedvezmeny = " + KEDVEZMENY + " WHERE szerszam = " + szerzodesszam + "";
            try {
                s.beginTransaction();
                s.createSQLQuery(qv).executeUpdate();
                s.getTransaction().commit();
                s.close();
            } catch (HibernateException erro) {
                s.getTransaction().rollback();
                s.close();
            }
            
        }else{
            Session s1 = NewHibernateUtil.getSessionFactory().openSession();
            String qv1 = "UPDATE Szerzodes SET  kedvezmeny = 0 WHERE szerszam = " + szerzodesszam + "";
            try {
                s1.beginTransaction();
                s1.createSQLQuery(qv1).executeUpdate();
                s1.getTransaction().commit();
                s1.close();
            } catch (HibernateException erro) {
                s1.getTransaction().rollback();
                s1.close();
            }
            
        }
        
        
        
        
        
    }
    
    
    
    
    
}
