package app.repositories;

import app.models.Scooter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ScootersRepositoryMock implements ScooterRepository{

    Scooter[] scooters;
    public ScootersRepositoryMock() {
        scooters = new Scooter[7];

    }

    @Bean
    public List<Scooter> getTestScooters(){
        return null;
    }

    @Override
    public List<Scooter> findAll() {
        long id = 30000;

        for (int i = 0; i < scooters.length; i++) {
            scooters[i] = Scooter.createSampleScooter(id);
            id++;
        }
        return Arrays.asList(scooters);
//        return null;
    }

    //TODO should implement unique id's with bean
}
