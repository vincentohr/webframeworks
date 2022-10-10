package app.repositories;

import app.models.Scooter;
import org.springframework.context.annotation.Bean;

import java.util.List;


public class ScootersRepositoryMock {
    @Bean
    public List<Scooter> getTestScooters(){

        //TODO should manage an array of scooters 7 in total with semi-random data
        return null;
    }

    //TODO should implement unique id's with bean
}
