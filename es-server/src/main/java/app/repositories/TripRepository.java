package app.repositories;

import app.models.Trip;

import java.util.List;

public interface TripRepository {
    List<Trip> findAll();
    Trip findById(long id);

    Trip save(Trip trip);

    Trip deleteById(Long id);
}
