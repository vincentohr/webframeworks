package app.repositories;

import app.models.Scooter;
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
public class ScooterRepositoryJpa implements ScooterRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Scooter> findAll() {
        TypedQuery<Scooter> query =
                this.entityManager.createQuery(
                        "SELECT s FROM Scooter s", Scooter.class
                );
        return query.getResultList();
    }

    @Override
    public Scooter findById(long id) {
        TypedQuery<Scooter> query =
                this.entityManager.createQuery(
                        "SELECT s FROM Scooter s WHERE s.id = ?1", Scooter.class
                );
        return query.setParameter(1, id).getSingleResult();
    }

    @Override
    public Scooter save(Scooter scooter) {
        entityManager.merge(scooter);
        return scooter;
    }

    @Override
    public Scooter deleteById(Long id) {
        return null;
    }
}
