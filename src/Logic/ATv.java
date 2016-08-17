/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Data.Tv;
import Data.Ugyfel;
import Hibernate.NewHibernateUtil;
import java.util.List;
import java.util.Vector;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Imre
 */
public class ATv {

    private Vector<Object> tvVector;
    private List list;
    

    public Vector csomagok() {
        executeHQLQuery();
        return tvVector;

    }

    private void executeHQLQuery() {

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try {

            session.beginTransaction();
            Query q = session.createQuery("from Tv t ");
           this.list = q.list();
            displayResult();
            session.getTransaction().commit();
            

        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            session.close();
        }
    }

    private void displayResult() {

        Vector<Object> adatok = new Vector<>();

        for (Object object : this.list) {
            Tv u = (Tv) object;
            Vector<Object> egySor = new Vector<>();
            egySor.add(u.getTvid());
            egySor.add(u.getTvcsnev());
            egySor.add(u.getTvhdij());

            adatok.add(egySor);

        }

        this.tvVector = adatok;

    }
    
   

}
