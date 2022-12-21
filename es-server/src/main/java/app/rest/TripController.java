package app.rest;

import app.Exceptions.PreConditionFailedException;
import app.Exceptions.ResourceNotFoundException;
import app.models.Scooter;
import app.models.Trip;
import app.repositories.TripsRepositoryJpa;
import app.serialize.CustomJson;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripController {

    @Autowired
    TripsRepositoryJpa tripRepository;

    @JsonSerialize(using = CustomJson.ShallowSerializer.class)
    @GetMapping("")
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    @PutMapping("{id}")
    public void updateScooter(@RequestParam(required = false) String status) throws Exception {

    }
}
