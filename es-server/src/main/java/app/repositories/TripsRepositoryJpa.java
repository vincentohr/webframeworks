package app.repositories;

import app.models.Trip;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@Primary
public class TripsRepositoryJpa implements TripRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Trip> findAll() {
        TypedQuery<Trip> query =
                this.entityManager.createQuery(
                        "SELECT t FROM Trip t", Trip.class
                );
        return query.getResultList();
    }

    @Override
    public Trip findById(long id) {
        return null;
    }

    @Override
    public Trip save(Trip trip) {
        entityManager.merge(trip);
        return trip;
    }

    @Override
    public Trip deleteById(Long id) {
        return null;
    }
}
