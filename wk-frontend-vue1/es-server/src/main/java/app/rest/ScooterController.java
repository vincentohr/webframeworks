package app.rest;

import app.models.Scooter;
import app.repositories.ScooterRepository;
import app.repositories.ScootersRepositoryMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/scooters")
public class ScooterController {

    ScooterRepository scooterRepository = new ScootersRepositoryMock();
    @GetMapping("/test")
    public List<Scooter> getTestScooters() {
        return
                List.of(
                        new Scooter("Test-scooter-A"),
                        new Scooter("Test-scooter-B")
                        );

    }
    @GetMapping("/random")
    public List<Scooter> getAllScooters(){
        return scooterRepository.findAll();
    }

}

