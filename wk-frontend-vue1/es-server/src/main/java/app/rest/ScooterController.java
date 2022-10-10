package app.rest;

import app.models.Scooter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/scooters")
public class ScooterController {
    @GetMapping("test")

    public List<Scooter> getTestScooters(){
        return
                List.of(
                new Scooter("Test-scooter-A"),
                new Scooter("Test-scooter-B")
        );

    }

}

