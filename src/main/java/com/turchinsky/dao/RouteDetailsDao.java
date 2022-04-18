package com.turchinsky.dao;

import com.turchinsky.entities.RouteDetailsEntity;
import com.turchinsky.entities.RouteDetailsEntityPK;
import com.turchinsky.entities.RouteEntity;
import com.turchinsky.entities.StationEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        return Optional.ofNullable(query.getResultList()).orElse(Collections.emptyList());
    }

    public List<Integer> getNextStationOrder(int routeId) {
        Query query = entityManager
                .createQuery("SELECT e.stationOrder from RouteDetailsEntity e where e.routeId=" + routeId);
        return Optional.ofNullable(query.getResultList()).orElse(Collections.emptyList());
    }


    public List<RouteDetailsEntity> getRouteDetailsByRouteId(int routeId) {
        Query query = entityManager
                .createQuery(
                        "SELECT e from RouteDetailsEntity e where e.routeId = " + routeId + " order by e.stationOrder");
        return Optional.ofNullable(query.getResultList()).orElse(Collections.emptyList());
    }

    public List<Integer> getUsedStations(int routeId) {
        Query query = entityManager
                .createQuery("SELECT e.stationId FROM RouteDetailsEntity e WHERE e.routeId=" + routeId);
        return Optional.ofNullable(query.getResultList()).orElse(Collections.emptyList());
    }

    public int getMaxKm(int routeId) {
        Query query = entityManager
                .createQuery("SELECT MAX (e.km) from RouteDetailsEntity e where e.routeId=" + routeId);
        return (int) Optional.ofNullable(query.getSingleResult())
                .orElse(0);
    }

    public int getKmByStationOrder(int routeId, int stationOrder) {
        Query query = entityManager.createQuery(
                "SELECT e.km from RouteDetailsEntity e where e.routeId=" +
                        routeId + " and e.stationOrder=" + stationOrder);
        return (int) Optional.ofNullable(query.getSingleResult())
                .orElse(
//                        0
                        new RouteDetailsEntity(
                                new RouteEntity(0, ""), new StationEntity(0, "")).getKm()
                       );
    }

    public List<RouteDetailsEntity> getAllAfter(RouteDetailsEntity routeDetailsEntity) {
        Query query = entityManager.createQuery(
                "select e from RouteDetailsEntity e where e.routeId = " + routeDetailsEntity
                        .getRouteId() + " and e.stationOrder>" + routeDetailsEntity
                        .getStationOrder() + " order by e.stationOrder");
        return Optional.ofNullable(query.getResultList()).orElse(Collections.emptyList());
    }

    public void updateStationOrder(RouteDetailsEntity entity, int newOrder) {
        /// TODO: 10/21/2020 Change this
//        Query query = entityManager.createQuery(
//                "UPDATE RouteDetailsEntity  e set e.stationOrder = " + newOrder + " where e.routeId = " + entity
//                        .getRouteId() + " and e.stationOrder = " + entity.getStationOrder());
        entityManager.getTransaction().begin();
        entity.setStationOrder(newOrder);
        entityManager.getTransaction().commit();
    }

    public RouteDetailsEntity update(RouteDetailsEntity routeDetailsEntity) {
        entityManager.getTransaction().begin();
        entityManager.merge(routeDetailsEntity);
        entityManager.getTransaction().commit();
        return routeDetailsEntity;
    }

    @Override
    public RouteDetailsEntity get(RouteDetailsEntityPK routeDetailsEntityPK) {
        return Optional.ofNullable(entityManager.find(RouteDetailsEntity.class, routeDetailsEntityPK))
                .orElse(new RouteDetailsEntity(new RouteEntity(0, ""), new StationEntity(0, "")));
    }

    @Override
    public List<RouteDetailsEntity> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM RouteDetailsEntity e");
        return Optional.ofNullable(query.getResultList()).orElse(Collections.emptyList());
    }


    @Override
    public RouteDetailsEntity save(RouteDetailsEntity routeDetailsEntity) {
        entityManager.getTransaction().begin();
        entityManager.persist(routeDetailsEntity);
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
