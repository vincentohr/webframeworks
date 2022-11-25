package app.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Trip implements Identifiable {
    @Id
    @GeneratedValue
    private long id;

    @JsonFormat(pattern = "MM-dd-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime startDateTime;

    @JsonFormat(pattern = "MM-dd-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime endDateTime;

    private String startPosition;

    private String endPosition;

    private double mileage;

    private double cost;

    @ManyToOne(cascade = CascadeType.ALL)
    private Scooter scooter;

    public Trip(long id, LocalDateTime startDateTime, LocalDateTime endDateTime, String startPosition,
                String endPosition, double mileage, double cost) {
        this.id = id;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.mileage = mileage;
        this.cost = cost;
    }

    public Trip(@JsonProperty long id) {
        this.id = id;
    }

    public Trip() {
    }

    public static Trip createSampleTrip(long id) {
        LocalDateTime[] localDateTime = {LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(6),
                LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(5)};
        double latitude = 52.3702157;
        double longitude = 4.895167899999933;

        Trip trip = new Trip(id);
        trip.mileage = (int) (Math.random() * 95) + 5;
        trip.cost = (int) (Math.random() * 10_000);
        trip.startDateTime = localDateTime[(int) (Math.random() * localDateTime.length - 1)];
        trip.endDateTime = localDateTime[(int) (Math.random() * localDateTime.length - 1)].plusWeeks(2);
        trip.startPosition = latitude + " " + longitude;
        trip.endPosition = latitude + " " + longitude;
        trip.setScooter(Scooter.createSampleScooter(0));
        return trip;
    }

    public boolean associateScooter(Scooter scooter) {
        if(scooter == null || this.getScooter().getId() != scooter.getId()){
            return false;
        }
        this.setScooter(scooter);
        scooter.associateTrip(this);
        return true;
    }



//    public boolean dissociateScooter(Scooter scooter) {
//        if(scooter != null || this.getScooter().getId() == scooter.getId()) {
//            scooter.dissociateTrip(this);
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

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(String startPosition) {
        this.startPosition = startPosition;
    }

    public String getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(String endPosition) {
        this.endPosition = endPosition;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Scooter getScooter() {
        return scooter;
    }

    public void setScooter(Scooter scooter) {
        this.scooter = scooter;
    }
}
