package android.readfiledemo;

public class SV {
    String mssv,hten;
    int nsinh;

    public SV(String mssv, String hten, int nsinh) {
        this.mssv = mssv;
        this.hten = hten;
        this.nsinh = nsinh;
    }
    public SV()
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

    public int getNsinh() {
        return nsinh;
    }

    public void setNsinh(int nsinh) {
        this.nsinh = nsinh;
    }
    public String printSV()
    {
        return "Mssv: "+mssv + "-- Họ tên: " + hten + "-- Năm sinh: " + nsinh + "\n";

    }
}
