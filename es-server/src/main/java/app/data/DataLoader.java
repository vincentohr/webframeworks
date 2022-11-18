package app.data;

import app.models.Scooter;
import app.repositories.ScooterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Override
    @Transactional
    public void run(String... args) {
        System.out.println("Running CommandLine Startup");
        this.createInitialScooters();
    }

    @Autowired
    private ScooterRepository scooterRepository;


    private void createInitialScooters() {
        List<Scooter> scooters = this.scooterRepository.findAll();
        if (scooters.size() > 0)
            return;
        System.out.println("Configuring some initial scooters in the repository");
        for (int i = 0; i < 10; i++) {
            this.scooterRepository.save(Scooter.createSampleScooter(0));
        }
    }

}
