package android.readfiledemo;

public class CS {
    String hten, qgia, loai;

    public CS(String hten, String qgia, String loai) {
        this.hten = hten;
        this.qgia = qgia;
        this.loai = loai;
    }
    public CS()
    {

    }

    public String getHten() {
        return hten;
    }

    public void setHten(String hten) {
        this.hten = hten;
    }

    public String getQgia() {
        return qgia;
    }

    public void setQgia(String qgia) {
        this.qgia = qgia;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }
    public String toString()
    {
        return hten + " -- " + qgia + " -- " + loai +"\n";
    }
}
