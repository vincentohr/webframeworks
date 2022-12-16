package app.rest;

import app.models.Trip;
import app.repositories.TripsRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:*")
@RequestMapping("/trips")
public class TripsController {

    @Autowired
    TripsRepositoryJpa tripRepository;

    @GetMapping("")
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }
}
