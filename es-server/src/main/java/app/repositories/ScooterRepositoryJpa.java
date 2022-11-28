package app.repositories;

import app.models.Scooter;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@Primary
public class ScooterRepositoryJpa extends AbstractEntityRepositoryJpa<Scooter> {

    public ScooterRepositoryJpa() {
        super(Scooter.class);
    }

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
    public Scooter deleteById(long id) {
        return null;
    }
    @Override
    public List<Scooter> findByQuery(String jpqlName, Object params) {
        return null;
    }
}
