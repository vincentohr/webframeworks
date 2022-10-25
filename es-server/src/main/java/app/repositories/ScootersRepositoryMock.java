package app.repositories;

import app.models.Scooter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScootersRepositoryMock implements ScooterRepository {
    private List<Scooter>  scootersList = new ArrayList<>();
    private int count = 0;

    @Bean
    public List<Scooter> getTestScooters() {
        return null;
    }

    public List<Scooter> getRandomScooters() {
        long id = 30000;
        int amtRandomScooters = 7;
        for (int i = 0; i < amtRandomScooters; i++) {
            Scooter scooter;
            scooter = Scooter.createSampleScooter(id);
            scootersList.add(scooter);
            id++;
        }
        return scootersList;
    }

    @Override
    public List<Scooter> findAll() {
        if(count == 0){
            scootersList = getRandomScooters();
        }
        count++;
        return scootersList;
    }

    @Override
    public Scooter findById(long id) {
        for (Scooter scooter : scootersList) {
            if (scooter != null && scooter.getId() == id) {
                return scooter;
            }
        }
        return null;
    }

    @Override
    public Scooter save(Scooter scooter) {
        if(scooter.getId() == 0){
            scooter.setId((int)(Math.random() * 1000) + 30000);
        }
        scootersList.add(scooter);
        return scooter;
    }

    @Override
    public Scooter deleteById(Long id) {
        for (int i = 0; i < scootersList.size(); i++) {
            if (scootersList.get(i).getId() == id) {
                scootersList.remove(scootersList.get(i));
            }
        }
        return null;
    }
}
