package app.rest;

import app.Exceptions.PreConditionFailedException;
import app.Exceptions.ResourceNotFoundException;
import app.models.Trip;
import app.repositories.TripsRepositoryJpa;
import app.serialize.CustomJson;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @PutMapping("/{tripId}")
    public void updateTrip(@RequestParam(required = false) boolean finish, @PathVariable long tripId,
                           @RequestBody Trip tripDetails) throws Exception {
        Trip updateTrip = tripRepository.findById(tripId);
//        if (!finish) {
//            return;
//        }
        if (updateTrip == null) {
            throw new ResourceNotFoundException(tripId);
        } else if (tripId != tripDetails.getId() && tripDetails.getId() != 0) {
            throw new PreConditionFailedException(tripId, tripDetails.getId());
        } else {
            updateTrip.setScooter(tripDetails.getScooter());
            updateTrip.setStartDateTime(tripDetails.getStartDateTime());
            updateTrip.setEndDateTime(LocalDateTime.now());
            updateTrip.setStartPosition(tripDetails.getStartPosition());
            updateTrip.setEndPosition(tripDetails.getEndPosition());
            updateTrip.setMileage(tripDetails.getMileage());
            tripRepository.save(updateTrip);
        }
    }
}
