package app.rest;

import app.models.Scooter;
import app.repositories.ScooterRepository;
import app.repositories.ScootersRepositoryMock;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/scooters")
public class ScooterController {

    @Autowired
    ScooterRepository scooterRepository = new ScootersRepositoryMock();

    @GetMapping("/test")
    public List<Scooter> getTestScooters() {
        return
                List.of(
                        new Scooter("Test-scooter-A"),
                        new Scooter("Test-scooter-B")
                );

    }

    @GetMapping("")
    public List<Scooter> getAllScooters() {
        return scooterRepository.findAll();
    }

    @GetMapping("{id}")
    public Scooter getScooterById(@PathVariable long id) throws Exception{
        Scooter scooter = scooterRepository.findById(id);

        if (scooter != null)
            return scooterRepository.findById(id);
        else {
            throw new Exception("Scooter not exists with id: " + id);
        }
    }

    @PostMapping()
    public void createScooter(@RequestBody Scooter scooter) {
       Scooter savedScooter = scooterRepository.save(scooter);
    }

    @PutMapping("{id}")
    public void updateScooter(@PathVariable long id, @RequestBody Scooter scooterDetails) throws Exception {
        Scooter updateScooter = scooterRepository.findById(id);
        if(updateScooter == null){
            throw new Exception("Scooter not exist with id: " + id);
        } else {
            updateScooter.setTag(scooterDetails.getTag());
            updateScooter.setBatteryCharge(scooterDetails.getBatteryCharge());
            updateScooter.setStatus(scooterDetails.getStatus());
            updateScooter.setGpsLocation(scooterDetails.getGpsLocation());
            updateScooter.setMileage(scooterDetails.getMileage());
        }
    }

    @DeleteMapping("{id}")
    public void deleteScooter(@PathVariable long id) throws Exception{
        Scooter scooterId = scooterRepository.findById(id);
        if(scooterId == null){
            throw new Exception("Scooter not exist with id: " + id);
        } else {
            scooterRepository.deleteById(id);
        }
    }
    @JsonView(View.class)
    @GetMapping("/summary")
    public List<Scooter> getScootersSummary(){
        return scooterRepository.findAll();
    }
}

