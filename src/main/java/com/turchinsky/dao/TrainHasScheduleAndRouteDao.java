package com.turchinsky.dao;

import com.turchinsky.entities.TrainHasScheduleAndRouteEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.List;

@Component
@Repository
public class TrainHasScheduleAndRouteDao
        implements Dao<TrainHasScheduleAndRouteEntity> {

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

    public TrainHasScheduleAndRouteEntity getByTrainRoteDepartureTime(int trainId, int routeId,
                                                                      Timestamp departureTime) {
        Query query = entityManager.createQuery(
                "SELECT e FROM TrainHasScheduleAndRouteEntity e where e.trainId=" +
                        trainId + " and e.routeId=" + routeId + " and e.departureTime='" + departureTime + "'");
        return (TrainHasScheduleAndRouteEntity) query.getSingleResult();
    }

    @Override public TrainHasScheduleAndRouteEntity get(int id) {
        return entityManager.find(TrainHasScheduleAndRouteEntity.class, id);
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
