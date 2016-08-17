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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Imre
 */
public class ASzerzodes {

    private Vector<Object> szerVector;
    private int folyoszamla;

    public int generalSzerSzam() {

        Session ses = NewHibernateUtil.getSessionFactory().openSession();

        Query q = ses.createQuery("from Szerzodes sz ");
        Szerzodes szeg;
        int id = 0;
        List resultList = q.list();
        if (resultList.size() > 0) {
            szeg = (Szerzodes) resultList.get(resultList.size() - 1);
            return id = szeg.getSzerszam() + 1;
        } else {
            return id = 1;
        }

    }

    public void felveszSzolgaltatas(int szersz, int csomag, int folyo) {
        int a = 0, b = 0, c = 0, d = 0;
        String aa = "";
        String table = "";
        double csomagHavi = 0;

        if (1000 < csomag && csomag < 2000 && csomag != 1004) {
            aa = "internet";
            table = "Net";
        } else if (csomag == 1004) {
            aa = "fixip";
            table = "Net";
        } else if (2000 < csomag && csomag < 3000) {
            aa = "telefon";
            table = "Telefon";
        } else if (3000 < csomag && csomag < 4000) {
            aa = "tv";
            table = "Tv";
        }

        Szerzodes sze = new Szerzodes();
        Set set = new HashSet();
        Session sese = NewHibernateUtil.getSessionFactory().openSession();
        Query q = sese.createQuery("from Szerzodes sz ");
        List rList = q.list();
        sese.close();

        Szerzodes er = new Szerzodes();
        for (Object object : rList) {

            er = (Szerzodes) object;
            int id = er.getSzerszam();

            set.add(id);
        }

        Session stb = NewHibernateUtil.getSessionFactory().openSession();
        Query qtab = stb.createQuery("from " + table + " t ");
        List szList = qtab.list();
        stb.close();

        if (table.equals("Net")) {
            for (Object object : szList) {
                Net n = (Net) object;
                if (n.getNetid() == csomag) {
                    csomagHavi = n.getNethdij();
                }
            }
        } else if (table.equals("Telefon")) {
            for (Object object : szList) {
                Telefon t = (Telefon) object;
                if (t.getTid() == csomag) {
                    csomagHavi = t.getThdij();
                }
            }
        } else if (table.equals("Tv")) {
            for (Object object : szList) {
                Tv tv = (Tv) object;
                if (tv.getTvid() == csomag) {
                    csomagHavi = tv.getTvhdij();
                }
            }
        }

        if (set.contains(szersz)) {

            Session stt = NewHibernateUtil.getSessionFactory().openSession();
            Query qta = stt.createQuery("from Szerzodes szew Where szerszam= " + szersz + "");
            List sz2List = qta.list();
            stt.close();

            Szerzodes ser = (Szerzodes) sz2List.get(0);
            int inter = 0;
            int tel = 0;
            int teve = 0;
            int regihavidij = 0;

            if (aa.equals("internet")) {
                inter = ser.getInternet();
            } else if (aa.equals("telefon")) {
                tel = ser.getTelefon();
            } else if (aa.equals("tv")) {
                teve = ser.getTv();
            }
            if (inter > 0) {
                for (Object object : szList) {
                    Net n = (Net) object;
                    if (n.getNetid() == inter) {
                        regihavidij = n.getNethdij();
                    }
                }
            } else if (tel > 0) {

                for (Object object : szList) {
                    Telefon t = (Telefon) object;
                    if (t.getTid() == tel) {
                        regihavidij = t.getThdij();
                    }
                }

            } else if (teve > 0) {
                for (Object object : szList) {
                    Tv tv = (Tv) object;
                    if (tv.getTvid() == teve) {
                        regihavidij = tv.getTvhdij();
                    }
                }
            }
            csomagHavi = csomagHavi - regihavidij;

            Session s = NewHibernateUtil.getSessionFactory().openSession();
            String qv = "UPDATE Szerzodes SET " + aa + " = " + csomag + ", havidij = havidij + " + csomagHavi + " WHERE szerszam = " + szersz + "";
            try {
                s.beginTransaction();
                s.createSQLQuery(qv).executeUpdate();
                s.getTransaction().commit();
                s.close();
            } catch (HibernateException erro) {
                s.getTransaction().rollback();
                s.close();
            }

        } else {

            if (1000 < csomag && csomag < 2000 && csomag != 1004) {
                a = csomag;
            } else if (csomag == 1004) {
                b = csomag;
            } else if (2000 < csomag && csomag < 3000) {
                c = csomag;
            } else if (3000 < csomag && csomag < 4000) {
                d = csomag;
            }

            Session ses2 = NewHibernateUtil.getSessionFactory().openSession();
            ses2.beginTransaction();

            sze.setSzerszam(szersz);
            sze.setFolyosz(folyo);
            sze.setInternet(a);
            sze.setFixip(b);
            sze.setTelefon(c);
            sze.setTv(d);
            sze.setHavidij(csomagHavi);

            ses2.save(sze);
            ses2.getTransaction().commit();
            ses2.close();
        }

    }

    public void torolSzolgaltatas(int szersza, int csomag) {
        String table1 = "";
        String ab = "";

        int k = 1;

        if (1000 < csomag && csomag < 2000 && csomag != 1004) {
            ab = "internet";
            table1 = "Net";
            k = 0;
        } else if (csomag == 1004) {
            ab = "fixip";
            table1 = "Net";
            k = 0;
        } else if (2000 < csomag && csomag < 3000) {
            ab = "telefon";
            table1 = "Telefon";
            k = 0;
        } else if (3000 < csomag && csomag < 4000) {
            ab = "tv";
            table1 = "Tv";
            k = 0;
        }

        double csomagHavi = 0;
        Session stb = NewHibernateUtil.getSessionFactory().openSession();
        Query qtab = stb.createQuery("from " + table1 + " tab ");
        List szList = qtab.list();
        stb.close();
        if (table1.equals("Net")) {
            for (Object object : szList) {
                Net n = (Net) object;
                if (n.getNetid() == csomag) {
                    csomagHavi = n.getNethdij();
                }
            }
        } else if (table1.equals("Telefon")) {
            for (Object object : szList) {
                Telefon t = (Telefon) object;
                if (t.getTid() == csomag) {
                    csomagHavi = t.getThdij();
                }
            }
        } else if (table1.equals("Tv")) {
            for (Object object : szList) {
                Tv tv = (Tv) object;
                if (tv.getTvid() == csomag) {
                    csomagHavi = tv.getTvhdij();
                }
            }
        }

        Session s3 = NewHibernateUtil.getSessionFactory().openSession();
        String qv1 = "UPDATE Szerzodes SET " + ab + " = " + k + ", havidij=havidij-" + csomagHavi + " WHERE szerszam = " + szersza + "";
        try {
            s3.beginTransaction();
            s3.createSQLQuery(qv1).executeUpdate();
            s3.getTransaction().commit();
            s3.close();
        } catch (HibernateException erro) {
            s3.getTransaction().rollback();
            s3.close();
        }

    }

    public Vector csomagok(int f) {

        this.folyoszamla = f;
        executeHQLQuery();

        return szerVector;

    }

    private void executeHQLQuery() {

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try {

            session.beginTransaction();
            Query q = session.createQuery("from Szerzodes tf where folyosz= '" + folyoszamla + "'");
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
            Szerzodes u = (Szerzodes) object;
            Vector<Object> egySor = new Vector<>();
            egySor.add(u.getSzerszam());

            egySor.add(u.getTv());
            egySor.add(u.getTelefon());
            egySor.add(u.getInternet());
            egySor.add(u.getFixip());
            adatok.add(egySor);

        }

        this.szerVector = adatok;

    }

}
