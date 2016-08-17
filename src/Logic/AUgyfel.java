/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Data.Ugyfel;
import Hibernate.NewHibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Imre
 */
public class AUgyfel {
    
    private int folyosz;
    private String nev;
    private String cim;
    private int statusz = 1;
    
    public int getFolyosz() {
        return this.folyosz;
    }
    
    public String getNev() {
        return nev;
    }
    
    public void setNev(String nev) {
        this.nev = nev;
    }
    
    public String getCim() {
        return cim;
    }
    
    public void setCim(String cim) {
        this.cim = cim;
    }
    
    public int getStatusz() {
        return statusz;
    }
    
    public AUgyfel() {
        
        Session s = NewHibernateUtil.getSessionFactory().openSession();
        
        Query q1 = s.createQuery("from Ugyfel u  ");
        
        List result = q1.list();
        Ugyfel u = (Ugyfel) result.get(result.size() - 1);
        folyosz = u.getFolyosz();
        
        folyosz += 1;
        
    }
    
    public void adatIr() {
        Session se = NewHibernateUtil.getSessionFactory().openSession();
        se.beginTransaction();
        
        Ugyfel ugy = new Ugyfel();
        ugy.setFolyosz(folyosz);
        ugy.setNev(nev);
        ugy.setCim(cim);
        ugy.setStatusz(statusz);
        
        se.save(ugy);
        se.getTransaction().commit();
        se.close();
    }
    
}
