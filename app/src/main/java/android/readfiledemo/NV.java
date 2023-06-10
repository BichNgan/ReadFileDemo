package android.readfiledemo;

public class NV {
    String msnv, ten, sdt,chucvu;

    public String getMsnv() {
        return msnv;
    }

    public NV() {
    }

    public NV(String msnv, String ten, String sdt, String chucvu) {
        this.msnv = msnv;
        this.ten = ten;
        this.sdt = sdt;
        this.chucvu = chucvu;
    }

    public void setMsnv(String msnv) {
        this.msnv = msnv;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getChucvu() {
        return chucvu;
    }

    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
    }
    public String printNV()
    {
        String kq="";
        kq=kq+"  " + msnv + " " + ten + " " + sdt + " " + chucvu +" \n";
        return  kq;
    }
}
