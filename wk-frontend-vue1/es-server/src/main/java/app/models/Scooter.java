package app.models;

import java.util.Random;

public class Scooter {
    public long id;
    public String tag;
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
    public Scooter(long id){
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
