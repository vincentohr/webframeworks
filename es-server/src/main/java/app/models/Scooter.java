package app.models;

import app.Views.IView;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Random;

@Entity
public class Scooter {
    @JsonView(value = { IView.SummaryView.class })
    @Id
    @GeneratedValue
    private long id;

    @JsonView(value = { IView.SummaryView.class })
    private String tag;

    @JsonView(value = { IView.SummaryView.class })
    private String status;

    private String gpsLocation;
    private double mileage;

    @JsonView(value = { IView.SummaryView.class })
    private int batteryCharge;

    @ManyToOne
    private Trip trip;

    public Scooter (){

    }
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
        String[] statusArray = {"IDLE", "INUSE", "MAINTENANCE"};
        Scooter scooter = new Scooter(id);
        scooter.batteryCharge = (int)(Math.random() * 95) + 5;
        scooter.mileage = (int)(Math.random() * 10_000);
        scooter.status = statusArray[(int)(Math.random()*3)];
        double latitude = 52.3702157;
        double longitude = 4.895167899999933;
        scooter.gpsLocation = latitude + " " + longitude;
        scooter.tag = scooter.generateRandomTag();
        scooter.associateTrip(new Trip());

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

    public boolean associateTrip(Trip trip){
        if(trip != null){
            trip.associateScooter(this);
            System.out.println(trip);
            return true;
        }
        return false;
    }

    public boolean dissociateTrip(Trip trip){
        return false;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
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
