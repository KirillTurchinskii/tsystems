package com.turchinsky.dao;

import com.turchinsky.entities.RouteDetailsEntity;
import com.turchinsky.entities.RouteDetailsEntityPK;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

@Component
@Repository
public class RouteDetailsDao implements ExtendedDao<RouteDetailsEntity, RouteDetailsEntityPK> {

    private static final EntityManagerFactory emFactoryObj;

//    private RouteService routeService;


    static {
        emFactoryObj = Persistence.createEntityManagerFactory("sbb-pu");
    }

    private final EntityManager entityManager = emFactoryObj.createEntityManager();

    public List<RouteDetailsEntity> getListByPK(RouteDetailsEntityPK routeDetailsEntityPK) {
        Query query = entityManager.createQuery(
                "SELECT e FROM RouteDetailsEntity e WHERE e.routeId=" + routeDetailsEntityPK
                        .getRouteId() + " and e.stationId=" + routeDetailsEntityPK
                        .getStationId() + " and e.stationOrder=" + routeDetailsEntityPK.getStationOrder());
        return query.getResultList();
    }

    public List<Integer> getNextStationOrder(int routeId) {
        Query query = entityManager
                .createQuery("SELECT e.stationOrder from RouteDetailsEntity e where e.routeId=" + routeId);
        return query.getResultList();
    }


    public List<RouteDetailsEntity> getRouteDetailsByRouteId(int routeId) {
        Query query = entityManager
                .createQuery(
                        "SELECT e from RouteDetailsEntity e where e.routeId = " + routeId + " order by e.stationOrder");
        return query.getResultList();
    }

    public List<Integer> getUsedStations(int routeId) {
        Query query = entityManager
                .createQuery("SELECT e.stationId FROM RouteDetailsEntity e WHERE e.routeId=" + routeId);
        return query.getResultList();
    }

    public int getMaxKm(int routeId) {
        Query query = entityManager
                .createQuery("SELECT MAX (e.km) from RouteDetailsEntity e where e.routeId=" + routeId);
        return (int) query.getSingleResult();
    }

    public int getKmByStationOrder(int routeId, int stationOrder) {
        Query query = entityManager.createQuery(
                "SELECT e.km from RouteDetailsEntity e where e.routeId=" + routeId + " and e.stationOrder=" + stationOrder);
        return (int) query.getSingleResult();
    }

    public List<RouteDetailsEntity> getAllAfter(RouteDetailsEntity routeDetailsEntity) {
        Query query = entityManager.createQuery(
                "select e from RouteDetailsEntity e where e.routeId = " + routeDetailsEntity
                        .getRouteId() + " and e.stationOrder>" + routeDetailsEntity
                        .getStationOrder() + " order by e.stationOrder");
        return query.getResultList();
    }

    public void updateStationOrder(RouteDetailsEntity entity, int newOrder) {
        Query query = entityManager.createQuery(
                "UPDATE RouteDetailsEntity  e set e.stationOrder = " + newOrder + " where e.routeId = " + entity
                        .getRouteId() + " and e.stationOrder = " + entity.getStationOrder());
        entityManager.getTransaction().begin();
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }

//    public void updateKm(RouteDetailsEntity entity, int newKm) {
//        Query query = entityManager.createQuery("UPDATE RouteDetailsEntity SET e.st = "+entity.s);
//    }

    //    @Override
    public RouteDetailsEntity update(RouteDetailsEntity routeDetailsEntity) {
        entityManager.getTransaction().begin();
        entityManager.persist(routeDetailsEntity);
        entityManager.getTransaction().commit();
        return routeDetailsEntity;
    }

    @Override
    public RouteDetailsEntity get(RouteDetailsEntityPK routeDetailsEntityPK) {
        return entityManager.find(RouteDetailsEntity.class, routeDetailsEntityPK);
    }

    @Override
    public List<RouteDetailsEntity> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM RouteDetailsEntity e");
        return query.getResultList();
    }

    @Override
    public RouteDetailsEntity save(RouteDetailsEntity routeDetailsEntity) {
        entityManager.getTransaction().begin();
//        if (routeDetailsEntity.getRouteId() == 0 && routeDetailsEntity.getStationId() == 0 && routeDetailsEntity
//                .getStationOrder() == 0) {
//            entityManager.persist(routeDetailsEntity);
//        } else {
        entityManager.merge(routeDetailsEntity);
//        }
        entityManager.getTransaction().commit();
        return routeDetailsEntity;
    }

    @Override
    public void delete(RouteDetailsEntity routeDetailsEntity) {
        entityManager.getTransaction().begin();
        entityManager.remove(routeDetailsEntity);
        entityManager.getTransaction().commit();
    }

}
