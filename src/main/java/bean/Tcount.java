package bean;

/**
 * @ClassName Tcount
 * @Description TODO
 * @Author wk
 * @Date 2020/6/9 0009 08:22
 * @Version 1.0
 */
public class Tcount {
    private int tid;

    private String tname;

    private String tcode;

    public Tcount(int tid, String tname, String tcode) {
        this.tid = tid;
        this.tname = tname;
        this.tcode = tcode;
    }

    public Tcount() {
    }

    @Override
    public String toString() {
        return "Tcount{" +
                "tid=" + tid +
                ", tname='" + tname + '\'' +
                ", tcode='" + tcode + '\'' +
                '}';
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTcode() {
        return tcode;
    }

    public void setTcode(String tcode) {
        this.tcode = tcode;
    }
}
