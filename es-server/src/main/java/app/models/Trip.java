package app.models;

import app.Views.IView;
import app.serialize.CustomJson;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@NamedQuery(name = "Trip_find_by_scooterId_and_period",
        query = "SELECT t FROM Scooter s JOIN s.trips t WHERE s.id = ?1 AND " +
                "t.startDateTime BETWEEN ?2 AND ?3 AND t.endDateTime BETWEEN ?2 AND ?3"
)
public class Trip implements Identifiable {
    @Id
    @GeneratedValue
    private long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime startDateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
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
        double latitude = 52.3702157;
        double longitude = 4.895167899999933;

        Trip trip = new Trip(id);
        trip.mileage = (int) (Math.random() * 95) + 5;
        trip.cost = (int) (Math.random() * 10_000);
        trip.startDateTime = randomDate().atStartOfDay();
        trip.endDateTime = trip.startDateTime.plusWeeks(4);
        trip.startPosition = latitude + " " + longitude;
        trip.endPosition = latitude + " " + longitude;
        trip.setScooter(Scooter.createSampleScooter(0));
        return trip;
    }

    public boolean associateScooter(Scooter scooter) {
        if (scooter == null || this.getScooter().getId() != scooter.getId()) {
            return false;
        }
        this.setScooter(scooter);
        scooter.associateTrip(this);
        return true;
    }

    @Transient
    public boolean isActive() {
        if(LocalDateTime.now().isAfter(endDateTime) ||
                LocalDateTime.now().isBefore(startDateTime)){
            return false;
        }
        return associateScooter(scooter);
    }


    //    public boolean dissociateScooter(Scooter scooter) {
//        if(scooter != null || this.getScooter().getId() == scooter.getId()) {
//            scooter.dissociateTrip(this);
//            return true;
//        }
//        return false;
//    }

    public static LocalDate randomDate() {
        int fiftyDays = 75;
        int dateSinceEpochDay = LocalDate.now().getYear() - LocalDate.EPOCH.getYear();
        System.out.println(dateSinceEpochDay);
        return LocalDate.ofEpochDay(ThreadLocalRandom
                .current().nextInt(-fiftyDays, fiftyDays)).plusYears(dateSinceEpochDay + 1);
    }

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
