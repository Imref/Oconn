/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Data.EgyenlegAllitas;
import Data.Szerzodes;
import Hibernate.NewHibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Imre
 */
public class DijatSzamol {

    public void egyhaviDijFelszamitas() {

        Session stt = NewHibernateUtil.getSessionFactory().openSession();
        Query qta = stt.createQuery("from Szerzodes szew ");
        List szszList = qta.list();
        stt.close();
        Szerzodes szer = new Szerzodes();

        for (Object object : szszList) {

            szer = (Szerzodes) object;
            double egyenleg = szer.getEgyenleg();
            double havidij = szer.getHavidij();
            double kedvezmeny = szer.getKedvezmeny();
int szerszam = szer.getSzerszam();
            egyenleg = egyenleg - havidij * (1 - kedvezmeny);
            
            EgyenlegAllitas all = new EgyenlegAllitas();
            all.egyenlegetAllit(egyenleg, szerszam);

        }

    }
    
    public void befizetesOsszeg(int szer, double osszeg){
        
        Session stt = NewHibernateUtil.getSessionFactory().openSession();
        Query qta = stt.createQuery("from Szerzodes szed Where szerszam="+szer+" ");
        List szzList = qta.list();
        stt.close();
        Szerzodes sz5 = new Szerzodes();

        

            sz5 = (Szerzodes)szzList.get(0);
            double egyenleg = sz5.getEgyenleg();
            

            egyenleg += osszeg;
            
            
    EgyenlegAllitas all = new EgyenlegAllitas();
            all.egyenlegetAllit(egyenleg, szer);

}
}