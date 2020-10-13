package com.turchinsky.dao;

import com.turchinsky.entities.TrainHasScheduleAndRouteEntity;
import com.turchinsky.entities.TrainHasScheduleAndRouteEntityPK;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

@Component
@Repository
public class TrainHasScheduleAndRouteDao
        implements ExtendedDao<TrainHasScheduleAndRouteEntity, TrainHasScheduleAndRouteEntityPK> {

    private static final EntityManagerFactory emFactoryObj;

    static {
        emFactoryObj = Persistence.createEntityManagerFactory("sbb-pu");
    }

    private final EntityManager entityManager = emFactoryObj.createEntityManager();

    public TrainHasScheduleAndRouteEntity update(TrainHasScheduleAndRouteEntity trainHasScheduleAndRouteEntity) {
        entityManager.getTransaction().begin();
        entityManager.merge(trainHasScheduleAndRouteEntity);
        entityManager.getTransaction().commit();
        return trainHasScheduleAndRouteEntity;
    }

    @Override
    public TrainHasScheduleAndRouteEntity get(TrainHasScheduleAndRouteEntityPK trainHasScheduleAndRouteEntityPK) {
        return entityManager.find(TrainHasScheduleAndRouteEntity.class, trainHasScheduleAndRouteEntityPK);
    }

    @Override
    public List<TrainHasScheduleAndRouteEntity> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM TrainHasScheduleAndRouteEntity e");
        return query.getResultList();
    }

    @Override
    public TrainHasScheduleAndRouteEntity save(TrainHasScheduleAndRouteEntity trainHasScheduleAndRouteEntity) {
        entityManager.getTransaction().begin();
        entityManager.persist(trainHasScheduleAndRouteEntity);
        entityManager.getTransaction().commit();
        return trainHasScheduleAndRouteEntity;
    }

    @Override
    public void delete(TrainHasScheduleAndRouteEntity trainHasScheduleAndRouteEntity) {
        entityManager.getTransaction().begin();
        entityManager.remove(trainHasScheduleAndRouteEntity);
        entityManager.getTransaction().commit();
    }

}
