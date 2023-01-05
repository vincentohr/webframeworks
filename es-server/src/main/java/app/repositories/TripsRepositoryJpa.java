package app.repositories;

import app.models.Trip;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@Primary
public class TripsRepositoryJpa extends AbstractEntityRepositoryJpa<Trip> {

    public TripsRepositoryJpa() {
        super(Trip.class);
    }

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
        TypedQuery<Trip> query =
                this.entityManager.createQuery(
                        "SELECT t FROM Trip t WHERE t.id = ?1", Trip.class
                );
        query.setParameter(1, id);
        return query.getSingleResult();
    }

    @Override
    public Trip save(Trip trip) {
        entityManager.merge(trip);
        return trip;
    }

    @Override
    public Trip deleteById(long id) {
        return null;
    }

    @Override
    public List<Trip> findByQuery(String jpqlName, Object... params) {
        Query query = entityManager.createNamedQuery(jpqlName);
        query.setParameter(1, params[0]);
        query.setParameter(2, params[1]);
        query.setParameter(3, params[2]);

        return query.getResultList();
    }

    @Override
    public List<Trip> findAllIdleScooters() {
        return null;
    }
}
