package app.models;

public class Scooter {
    public long id;
    public String tag;
    public String[] Status = {"IDLE", "INUSE", "MAINENANCE"};
    public String status;
    public String gpsLocation;
    public double mileage;
    public int batteryCharge;

    public Scooter(String tag) {
        id = 0;
        this.tag = tag;
        status = null;
        gpsLocation = null ;
        batteryCharge = 0;
        mileage = 0.0;
    }

    //TODO add static createSampleScooter

    @Override
    public String toString() {
        return "Scooter{" +
                "id=" + id +
                ", tag='" + tag + '\'' +
                ", gpsLocation='" + gpsLocation + '\'' +
                ", mileage='" + mileage + '\'' +
                ", batteryCharge=" + batteryCharge +
                '}';
    }
}
