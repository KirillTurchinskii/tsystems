package com.turchinsky.dao;

import com.turchinsky.entities.PassengerEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@Component
public class PassengersDao implements Dao<PassengerEntity> {

    private static final EntityManagerFactory emFactoryObj;

    static {
        emFactoryObj = Persistence.createEntityManagerFactory("sbb-pu");
    }

    private final EntityManager entityManager = emFactoryObj.createEntityManager();

    public PassengerEntity update(PassengerEntity passengerEntity) {
        entityManager.getTransaction().begin();
        passengerEntity = entityManager.merge(passengerEntity);
        entityManager.getTransaction().commit();
        return passengerEntity;
    }

    @Override
    public PassengerEntity get(int id) {
        return Optional.ofNullable(entityManager.find(PassengerEntity.class, id)).orElse(
                new PassengerEntity(0, "", "", new Date(0), "", ""));
    }

    @Override
    public List<PassengerEntity> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM PassengerEntity e");
        return Optional.ofNullable(query.getResultList()).orElse(Collections.emptyList());
    }

    @Override
    public PassengerEntity save(PassengerEntity passengerEntity) {
        entityManager.getTransaction().begin();
        entityManager.persist(passengerEntity);
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
