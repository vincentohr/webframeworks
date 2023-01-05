package app.repositories;

import app.models.Scooter;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
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
    public List<Scooter> findAllIdleScooters() {
        TypedQuery<Scooter> query =
                this.entityManager.createQuery(
                        "SELECT s FROM Scooter s WHERE s.status = 'IDLE' ", Scooter.class
                );
        return query.getResultList();
    }
    @Override
    public Scooter findById(long id) {
        TypedQuery<Scooter> query =
                this.entityManager.createQuery(
                        "SELECT s FROM Scooter s WHERE s.id = ?1", Scooter.class
                );
        try {
            query.setParameter(1, id).getSingleResult();
        } catch (NoResultException ex) {
            return null; // trigger ResourceNotFoundException
        } catch (Exception ex){
            ex.printStackTrace();
        }
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
    public List<Scooter> findByQuery(String jpqlName, Object... params) {
        Query query = entityManager.createNamedQuery(jpqlName);
        query.setParameter(1, params[0]);

        return query.getResultList();
    }
}
