package com.turchinsky.dao;

import com.turchinsky.entities.PassengerEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

@Repository
@Component
public class PassengersDao implements Dao<PassengerEntity> {

    private static final EntityManagerFactory emFactoryObj;

    static {
        emFactoryObj = Persistence.createEntityManagerFactory("sbb-pu");
    }

    private final EntityManager entityManager = emFactoryObj.createEntityManager();


    @Override
    public PassengerEntity get(int id) {
        return entityManager.find(PassengerEntity.class, id);
    }

    @Override
    public List<PassengerEntity> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM PassengerEntity e");
        return query.getResultList();
    }

    @Override
    public PassengerEntity save(PassengerEntity passengerEntity) {
        entityManager.getTransaction().begin();
        if (passengerEntity.getId() == 0) {
            entityManager.persist(passengerEntity);
        } else {
            passengerEntity = entityManager.merge(passengerEntity);
        }
        entityManager.getTransaction().commit();
        return passengerEntity;
    }

    @Override
    public void delete(PassengerEntity passengerEntity) {
        entityManager.getTransaction().begin();
        entityManager.remove(passengerEntity);
        entityManager.getTransaction().commit();
    }

}
