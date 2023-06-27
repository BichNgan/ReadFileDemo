package android.readfiledemo;

public class NhanVien {
    String ten;
    int ns;


    public NhanVien(String ten, int ns) {
        this.ten = ten;
        this.ns = ns;
    }
    public NhanVien()
    {

    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getNs() {
        return ns;
    }

    public void setNs(int ns) {
        this.ns = ns;
    }
    public String toString()
    {
        return ten + " -- " + String.valueOf(ns);
    }

}
