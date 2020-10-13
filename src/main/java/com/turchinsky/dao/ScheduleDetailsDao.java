package com.turchinsky.dao;

import com.turchinsky.entities.ScheduleDetailsEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

@Component
@Repository
public class ScheduleDetailsDao implements Dao<ScheduleDetailsEntity> {

    private static final EntityManagerFactory emFactoryObj;

    static {
        emFactoryObj = Persistence.createEntityManagerFactory("sbb-pu");
    }

    private final EntityManager entityManager = emFactoryObj.createEntityManager();

    @Override
    public ScheduleDetailsEntity get(int id) {
        return entityManager.find(ScheduleDetailsEntity.class, id);
    }

    @Override
    public List<ScheduleDetailsEntity> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM TrainHasScheduleAndRouteEntity e");
        return query.getResultList();
    }

    @Override
    public ScheduleDetailsEntity save(ScheduleDetailsEntity scheduleDetailsEntity) {
        entityManager.getTransaction().begin();
        entityManager.persist(scheduleDetailsEntity);
        entityManager.getTransaction().commit();
        return scheduleDetailsEntity;
    }

    @Override
    public void delete(ScheduleDetailsEntity scheduleDetailsEntity) {
        entityManager.getTransaction().begin();
        entityManager.remove(scheduleDetailsEntity);
        entityManager.getTransaction().commit();
    }

}
