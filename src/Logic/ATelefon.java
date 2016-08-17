/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Data.Telefon;
import Data.Tv;
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
public class ATelefon {

    private Vector<Object> telefonVector;

    public Vector csomagok() {
        executeHQLQuery();
        return telefonVector;

    }

    private void executeHQLQuery() {

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try {

            session.beginTransaction();
            Query q = session.createQuery("from Telefon tf ");
            List list = q.list();
            displayResult(list);
            session.getTransaction().commit();
            System.out.println(list.size());

        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            session.close();
        }
    }

    private void displayResult(List list) {

        Vector<Object> adatok = new Vector<>();

        for (Object object : list) {
            Telefon u = (Telefon) object;
            Vector<Object> egySor = new Vector<>();
            egySor.add(u.getTid());
            egySor.add(u.getCsnev());
            egySor.add(u.getThdij());

            adatok.add(egySor);

        }

        this.telefonVector = adatok;

    }

}
