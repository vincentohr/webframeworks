package app.rest;

import app.models.Trip;
import app.repositories.TripsRepositoryJpa;
import app.serialize.CustomJson;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripController {

    @Autowired
    TripsRepositoryJpa tripRepository;

    @JsonView({CustomJson.Summary.class})
    @JsonSerialize(using = CustomJson.ShallowSerializer.class)
    @GetMapping("")
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }
}
