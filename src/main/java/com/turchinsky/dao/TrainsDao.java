package com.turchinsky.dao;

import com.turchinsky.entities.TrainEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
@Component
public class TrainsDao implements Dao<TrainEntity> {

    private static final EntityManagerFactory emFactoryObj;

    static {
        emFactoryObj = Persistence.createEntityManagerFactory("sbb-pu");
    }

    private final EntityManager entityManager = emFactoryObj.createEntityManager();

    private final List<TrainEntity> trainsList = new ArrayList<>();

    @Override
    public TrainEntity get(int id) {
        return entityManager.find(TrainEntity.class, id);
    }

    public List<TrainEntity> getByNumber(String number) {
        Query query = entityManager.createQuery("SELECT e FROM TrainEntity e where e.number=" + "'" + number + "'");
        return query.getResultList();
    }

    @Override
    public List<TrainEntity> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM TrainEntity e");
        return query.getResultList();
    }

    @Override
    public TrainEntity save(TrainEntity trainEntity) {
        entityManager.getTransaction().begin();
        if (trainEntity.getId() == 0) {
            entityManager.persist(trainEntity);
        } else {
            trainEntity = entityManager.merge(trainEntity);
        }
        entityManager.getTransaction().commit();
        return trainEntity;
    }


    @Override
    public void delete(TrainEntity trainEntity) {
        entityManager.getTransaction().begin();
        entityManager.remove(trainEntity);
        entityManager.getTransaction().commit();

    }

}
