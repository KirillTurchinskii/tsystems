package com.turchinsky.dao;


import com.turchinsky.entities.StationEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

@Repository
@Component
public class StationDao implements Dao<StationEntity> {

    private static final EntityManagerFactory emFactoryObj;

    static {
        emFactoryObj = Persistence.createEntityManagerFactory("sbb-pu");
    }

    private final EntityManager entityManager = emFactoryObj.createEntityManager();

    @Override
    public StationEntity get(int id) {
        return entityManager.find(StationEntity.class, id);
    }

    public List<StationEntity> getByName(String name) {
        Query query = entityManager.createQuery("SELECT e FROM StationEntity e where e.name=" + "'" + name + "'");
        return query.getResultList();
    }

    @Override
    public List<StationEntity> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM StationEntity e");
        return query.getResultList();
    }

    @Override
    public StationEntity save(StationEntity stationEntity) {
        entityManager.getTransaction().begin();
        if (stationEntity.getId() == 0) {
            entityManager.persist(stationEntity);
        } else {
            stationEntity = entityManager.merge(stationEntity);
        }
        entityManager.getTransaction().commit();
        return stationEntity;
    }

    @Override
    public void delete(StationEntity stationEntity) {
        entityManager.getTransaction().begin();
        entityManager.remove(stationEntity);
        entityManager.getTransaction().commit();
    }

}
