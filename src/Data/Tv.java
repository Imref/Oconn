package Data;
// Generated Aug 2, 2016 9:44:35 PM by Hibernate Tools 4.3.1



/**
 * Tv generated by hbm2java
 */
public class Tv  implements java.io.Serializable {


     private int tvid;
     private String tvcsnev;
     private int tvhdij;

    public Tv() {
    }

    public Tv(int tvid, String tvcsnev, int tvhdij) {
       this.tvid = tvid;
       this.tvcsnev = tvcsnev;
       this.tvhdij = tvhdij;
    }
   
    public int getTvid() {
        return this.tvid;
    }
    
    public void setTvid(int tvid) {
        this.tvid = tvid;
    }
    public String getTvcsnev() {
        return this.tvcsnev;
    }
    
    public void setTvcsnev(String tvcsnev) {
        this.tvcsnev = tvcsnev;
    }
    public int getTvhdij() {
        return this.tvhdij;
    }
    
    public void setTvhdij(int tvhdij) {
        this.tvhdij = tvhdij;
    }




}


