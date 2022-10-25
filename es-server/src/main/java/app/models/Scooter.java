package app.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.View;

import java.util.Random;

public class Scooter {
    @JsonView(View.class)
    private long id;

    @JsonView(View.class)
    private String tag;

    @JsonView(View.class)
    private String status;

    private String gpsLocation;
    private double mileage;

    @JsonView(View.class)
    private int batteryCharge;

    public Scooter(String tag) {
        id = 0;
        this.tag = tag;
        status = null;
        gpsLocation = null ;
        batteryCharge = 0;
        mileage = 0.0;
    }
    @JsonCreator
    public Scooter(@JsonProperty long id){
        this.id = id;
    }

    public static Scooter createSampleScooter(long id){
        String[] statusArray = {"IDLE", "INUSE", "MAINENANCE"};
        Scooter scooter = new Scooter(id);
        scooter.batteryCharge = (int)(Math.random() * 95) + 5;
        scooter.mileage = (int)(Math.random() * 10_000);
        scooter.status = statusArray[(int)(Math.random()*3)];
        double latitude = 52.3702157;
        double longitude = 4.895167899999933;
        scooter.gpsLocation = latitude + " " + longitude;
        scooter.tag = scooter.generateRandomTag();
        return scooter;
    }

    // Generating an 8 character long String
    public String generateRandomTag(){
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 8;
        Random random = new Random();
        StringBuilder builder = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            builder.append((char) randomLimitedInt);
        }
        return builder.toString();
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setGpsLocation(String gpsLocation) {
        this.gpsLocation = gpsLocation;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public void setBatteryCharge(int batteryCharge) {
        this.batteryCharge = batteryCharge;
    }

    public long getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }

    public String getStatus() {
        return status;
    }

    public String getGpsLocation() {
        return gpsLocation;
    }

    public double getMileage() {
        return mileage;
    }

    public int getBatteryCharge() {
        return batteryCharge;
    }

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
