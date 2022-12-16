package app.models;

import app.Views.IView;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Random;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "Scooter_find_by_status",
                query = "SELECT s FROM Scooter s WHERE s.status = ?1"),
        @NamedQuery(name = "Scooter_find_by_battery",
                query = "SELECT s FROM Scooter s WHERE s.batteryCharge < ?1")
})
public class Scooter implements Identifiable {
    @JsonView(value = {IView.SummaryView.class})
    @Id
    @GeneratedValue
    private long id;

    @JsonView(value = {IView.SummaryView.class})
    private String tag;

    @JsonView(value = {IView.SummaryView.class})
    private String status;

    private String gpsLocation;
    private double mileage;

    @JsonView(value = {IView.SummaryView.class})
    private int batteryCharge;

    @OneToMany(mappedBy = "scooter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "Sc", nullable = false)
    @JsonBackReference
    private Set<Trip> trips;

    public Scooter() {

    }

    public Scooter(String tag) {
        id = 0;
        this.tag = tag;
        status = null;
        gpsLocation = null;
        batteryCharge = 0;
        mileage = 0.0;
    }

    @JsonCreator
    public Scooter(@JsonProperty long id) {
        this.id = id;
    }

    public static Scooter createSampleScooter(long id) {
        double randomLocation = Math.random() * 0.05;
        String[] statusArray = {"IDLE", "INUSE", "MAINTENANCE"};
        Scooter scooter = new Scooter(id);
        scooter.batteryCharge = (int) (Math.random() * 95) + 5;
        scooter.mileage = (int) (Math.random() * 10_000);
        scooter.status = statusArray[(int) (Math.random() * 3)];
        double latitude = 52.3702157 * randomLocation;
        double longitude = 4.895167899999933 * randomLocation;
        scooter.gpsLocation = latitude + " " + longitude;
        scooter.tag = scooter.generateRandomTag();

        return scooter;
    }

    // Generating an 8 character long String
    public String generateRandomTag() {
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

    public boolean associateTrip(Trip trip) {
        if (trip == null || this.getTrips().contains(trip)) {
            return false;
        }
        this.getTrips().add(trip);
        trip.associateScooter(this);
        return true;
    }
//    public boolean dissociateTrip(Trip trip) {
//        if(trip != null || this.getTrips().contains(trip)){
//            this.getTrips();
//            trip.dissociateScooter(this);
//            return true;
//        }
//        return false;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGpsLocation() {
        return gpsLocation;
    }

    public void setGpsLocation(String gpsLocation) {
        this.gpsLocation = gpsLocation;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public int getBatteryCharge() {
        return batteryCharge;
    }

    public void setBatteryCharge(int batteryCharge) {
        this.batteryCharge = batteryCharge;
    }

    public Set<Trip> getTrips() {
        return trips;
    }

    public void setTrips(Set<Trip> trips) {
        this.trips = trips;
    }
}
