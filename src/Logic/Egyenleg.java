/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Data.Szerzodes;
import Data.Ugyfel;
import Hibernate.NewHibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Imre
 */
public class Egyenleg {
    
    
    
    public double egyenleg(int szersz){
        
        Szerzodes ugy = new Szerzodes();
        
          Session ses = NewHibernateUtil.getSessionFactory().openSession();
         Query u = ses.createQuery(" from Szerzodes sz where szerszam=" + szersz + "");
          List resultList = u.list();
        
           ugy = (Szerzodes)resultList.get(0);
             ses.close();
      
            return ugy.getEgyenleg();
            
    }        
    
    
}
