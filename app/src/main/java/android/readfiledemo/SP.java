package android.readfiledemo;

public class SP {
    String msp, tensp;
    int dgia;

    public SP(String msp, String tensp, int dgia) {
        this.msp = msp;
        this.tensp = tensp;
        this.dgia = dgia;
    }
    public SP()
    {

    }

    public String getMsp() {
        return msp;
    }

    public void setMsp(String msp) {
        this.msp = msp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getDgia() {
        return dgia;
    }

    public void setDgia(int dgia) {
        this.dgia = dgia;
    }
    public String printSP()
    {
        return "Mã sp: " + msp + " -- Tên sp: "+tensp + "-- Don gia: " + dgia + "\n";
    }
}
