package app.rest;

import app.Exceptions.PreConditionFailedException;
import app.Exceptions.ResourceNotFoundException;
import app.Views.IView;
import app.models.Scooter;
import app.repositories.ScooterRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/scooters")
public class ScooterController {

    @Autowired
    private ScooterRepository scooterRepository;

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
    public Scooter getScooterById(@PathVariable long id) throws Exception {
        Scooter scooter = scooterRepository.findById(id);

        if (scooter != null)
            return scooterRepository.findById(id);
        else {
            throw new ResourceNotFoundException(id);
        }
    }

    @PostMapping("")
    public ResponseEntity<Object> createScooter(@RequestBody Scooter scooter) {
        Scooter savedScooter = scooterRepository.save(scooter);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedScooter.getId()).toUri();

        return  ResponseEntity
                .created(location)
                .header("Nieuwe Scooter aangemaakt!")
                .body(scooterRepository.findById(savedScooter.getId()));
    }

    @PutMapping("{id}")
    public void updateScooter(@PathVariable long id, @RequestBody Scooter scooterDetails) throws Exception {
        Scooter updateScooter = scooterRepository.findById(id);
        if (updateScooter == null) {
            throw new ResourceNotFoundException(id);
        } else if (id !=scooterDetails.getId() && scooterDetails.getId() != 0) {
            throw new PreConditionFailedException(id,scooterDetails.getId());
        } else {
            updateScooter.setTag(scooterDetails.getTag());
            updateScooter.setBatteryCharge(scooterDetails.getBatteryCharge());
            updateScooter.setStatus(scooterDetails.getStatus());
            updateScooter.setGpsLocation(scooterDetails.getGpsLocation());
            updateScooter.setMileage(scooterDetails.getMileage());
            scooterRepository.save(updateScooter);
        }
    }

    @DeleteMapping("{id}")
    public void deleteScooter(@PathVariable long id) throws Exception {
        Scooter scooterId = scooterRepository.findById(id);
        if (scooterId == null) {
            throw new ResourceNotFoundException(id);
        } else {
            scooterRepository.deleteById(id);
        }
    }

    @JsonView(value = {IView.SummaryView.class})
    @GetMapping("/summary")
    public List<Scooter> getScootersSummary() {
        return scooterRepository.findAll();
    }
}

