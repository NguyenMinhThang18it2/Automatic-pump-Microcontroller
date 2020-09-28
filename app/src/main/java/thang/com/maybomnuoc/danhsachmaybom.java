package thang.com.maybomnuoc;

public class danhsachmaybom {
    private String SumTimeBom;
    private String solanbom;
    private String time;

    public danhsachmaybom() {
    }

    @Override
    public String toString() {
        return "danhsachmaybom{" +
                "SumTimeBom='" + SumTimeBom + '\'' +
                ", solanbom='" + solanbom + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public String getSumTimeBom() {
        return SumTimeBom;
    }

    public void setSumTimeBom(String sumTimeBom) {
        SumTimeBom = sumTimeBom;
    }

    public String getSolanbom() {
        return solanbom;
    }

    public void setSolanbom(String solanbom) {
        this.solanbom = solanbom;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public danhsachmaybom(String sumTimeBom, String solanbom, String time) {
        SumTimeBom = sumTimeBom;
        this.solanbom = solanbom;
        this.time = time;
    }
}
