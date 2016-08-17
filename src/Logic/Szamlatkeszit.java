/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Data.Net;
import Data.Szerzodes;
import Data.Telefon;
import Data.Tv;
import Hibernate.NewHibernateUtil;
import java.util.List;
import java.util.Vector;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Imre
 */
public class Szamlatkeszit {

    private String szolgtv;
    private String csomagtv;
    private String szolgtel;
    private String csomagtel;
    private String szolgnet;
    private String csomagnet;
    private String szolgip;
    private String csomagip;
    private int havitv;
    private int havitel;
    private int havinet;
    private int haviip;
    
    private double egyenleg;
    
    public Vector szamlaVector;
    
    
    public Vector szamlazo(int sz){
        szamlazas(sz);
        return szamlaVector;
    }
    
    private void szamlazas(int sz) {
        
        
        
            Session stty = NewHibernateUtil.getSessionFactory().openSession();
            Query qta = stty.createQuery("from Szerzodes szex Where szerszam= " + sz + "");
            List szrList = qta.list();
            stty.close();
        
            for (Object object : szrList) {
            Szerzodes szr = (Szerzodes) object;
                if (szr.getTv()>3000) {
                    szolgtv = "Tv";
                    int i = szr.getTv();
                    Tv tv = new Tv();
                    Session st = NewHibernateUtil.getSessionFactory().openSession();
                    tv = (Tv)st.get(Tv.class,i );
                    csomagtv = tv.getTvcsnev();
                    havitv = tv.getTvid();
                  st.close();
                }
                if (szr.getTelefon()>2000) {
                    szolgtel = "Telefon";
                    int i1 = szr.getTelefon();
                    Telefon tp = new Telefon();
                    Session st = NewHibernateUtil.getSessionFactory().openSession();
                    tp = (Telefon)st.get(Telefon.class,i1 );
                    csomagtel = tp.getCsnev();
                    havitel = tp.getThdij();
                  st.close();
                }
                if (szr.getInternet()>1000) {
                    szolgnet = "Internet";
                    int i2 = szr.getInternet();
                    Net tn = new Net();
                    Session st = NewHibernateUtil.getSessionFactory().openSession();
                    tn = (Net)st.get(Net.class,i2 );
                    csomagnet = tn.getNetcsnev();
                    havinet = tn.getNethdij();
                  st.close();
                }
                if (szr.getFixip()== 1004) {
                    szolgip = "FIX IP";
                    int i3 = szr.getFixip();
                    Net tip = new Net();
                    Session st = NewHibernateUtil.getSessionFactory().openSession();
                    tip = (Net)st.get(Net.class,i3 );
                    csomagip = tip.getNetcsnev();
                            haviip = tip.getNethdij();
                  st.close();
                }
 
            
            displayResult();
        }
        
        
    }
    
    private void displayResult() {

        Vector<Object> adatok = new Vector<>();

        
            Vector<Object> egySor = new Vector<>();
            egySor.add(szolgtv);
            egySor.add(csomagtv);
            egySor.add(havitv);
            
            adatok.add(egySor);
            Vector<Object> egySor1 = new Vector<>();
             egySor1.add(szolgtel);

            egySor1.add(csomagtel);
            egySor1.add(havitel);
            
            adatok.add(egySor1);
            Vector<Object> egySor2 = new Vector<>();
             egySor2.add(szolgnet);

            egySor2.add(csomagnet);
            egySor2.add(havinet);
            
            adatok.add(egySor2);
            
            Vector<Object> egySor3 = new Vector<>();
             egySor3.add(szolgip);

            egySor3.add(csomagip);
            egySor3.add(haviip);
            
            adatok.add(egySor3);

       

        this.szamlaVector = adatok;

    }
    
}
