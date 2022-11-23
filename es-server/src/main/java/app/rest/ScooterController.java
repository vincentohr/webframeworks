package app.rest;

import app.Exceptions.PreConditionFailedException;
import app.Exceptions.ResourceNotFoundException;
import app.Views.IView;
import app.models.Scooter;
import app.models.Trip;
import app.repositories.ScooterRepository;
import app.repositories.TripRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/scooters")
public class ScooterController {

    @Autowired
    private ScooterRepository scooterRepository;

    @Autowired
    private TripRepository tripRepository;

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

    //test
    @GetMapping("/trips")
    public List<Trip> getAllTrips(){
        return tripRepository.findAll();
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

        return ResponseEntity
                .created(location)
                .header(
                        "Nieuwe scooter aangemaakt!",
                        "Je hebt een scooter aangemaakt en er is niks fout gegaan."
                )
                .body(scooterRepository.findById(savedScooter.getId()));
    }


    // todo localDateTimeFormat check trip.java
    @PostMapping("{scooterId}/trips")
    public void addTripToScooter(@RequestBody Trip trip, @PathVariable long scooterId) throws Exception {
        Scooter scooterDetail = scooterRepository.findById(scooterId);

        if(scooterDetail == null){
            throw new ResourceNotFoundException(scooterId);
        }

        if (!scooterDetail.getStatus().equals("IDLE") || scooterDetail.getBatteryCharge() < 10) {
            throw new PreConditionFailedException(scooterId, scooterDetail.getId());
        } else {
            Trip savedTrip = tripRepository.save(trip);
            savedTrip.associateScooter(scooterDetail);
            if (savedTrip.getStartDateTime() == null) {
                savedTrip.setStartDateTime(LocalDateTime.now());
            }
            scooterDetail.setGpsLocation(scooterDetail.getGpsLocation());
            scooterDetail.setStatus("IN_USE");
        }
    }

    @PutMapping("{id}")
    public void updateScooter(@PathVariable long id, @RequestBody Scooter scooterDetails) throws Exception {
        Scooter updateScooter = scooterRepository.findById(id);
        if (updateScooter == null) {
            throw new ResourceNotFoundException(id);
        } else if (id != scooterDetails.getId() && scooterDetails.getId() != 0) {
            throw new PreConditionFailedException(id, scooterDetails.getId());
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

