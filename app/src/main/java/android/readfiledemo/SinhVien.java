package android.readfiledemo;

public class SinhVien {
    String mssv,hten,ns;

    public SinhVien(String mssv, String hten, String ns) {
        this.mssv = mssv;
        this.hten = hten;
        this.ns = ns;
    }
    public SinhVien()
    {

    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getHten() {
        return hten;
    }

    public void setHten(String hten) {
        this.hten = hten;
    }

    public String getNs() {
        return ns;
    }

    public void setNs(String ns) {
        this.ns = ns;
    }
    public String toString()
    {
        return mssv + "--" +hten + "--" +ns +"\n";
    }
}
