package com.turchinsky.dao;

import com.turchinsky.entities.ScheduleDetailsEntity;
import com.turchinsky.transfer.StationsAndTimeForTicket;
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

//    public List<ScheduleDetailsEntity> getByStationsAndTime(StationsAndTimeForTicket stationsAndTimeForTicket) {
//        Query query = entityManager.createQuery(
//                "SELECT e FROM ScheduleDetailsEntity e WHERE e.routeId IN (select r.id from RouteEntity r where exists(select rd from RouteDetailsEntity rd where rd.routeId=777 and rd.stationId=333 and exists (select rd2 from RouteDetailsEntity rd2 where rd2.routeId=rd.routeId and rd2.stationId=444)) )");
//        return null;
//    }

    public List<ScheduleDetailsEntity> getByRouteGroupId(int routeGroupId) {
        Query query =
                entityManager.createQuery("SELECT e FROM ScheduleDetailsEntity e where e.routeGroupId=" + routeGroupId);
        return query.getResultList();
    }

    public List<Integer> getUsedGroups() {
        Query query =
                entityManager.createQuery("SELECT e.routeGroupId FROM ScheduleDetailsEntity e group by e.routeGroupId");
        return query.getResultList();
    }

    public List<ScheduleDetailsEntity> getPairByGroupIdStationsAndTime(int i,
                                                                       StationsAndTimeForTicket stationsAndTimeForTicket) {
        Query query = entityManager.createQuery(
                "SELECT e FROM ScheduleDetailsEntity e WHERE e.routeId in (" +
                        "select r.id from RouteEntity r where exists (" +
                        "select rd from RouteDetailsEntity rd " +
                        "where rd.routeId=r.id and rd.stationId=" + stationsAndTimeForTicket
                        .getStationIdFrom() + " and exists (" +
                        "select rd2 from RouteDetailsEntity rd2 " +
                        "where rd2.routeId=rd.routeId and rd2.stationId=" + stationsAndTimeForTicket
                        .getStationIdTo() + ")))" +
                        "and e.arrivalTime between '" + stationsAndTimeForTicket.getTimeFrom() + "' " +
                        "and '" + stationsAndTimeForTicket.getTimeTo() + "' " +
                        "and (e.stationId=" + stationsAndTimeForTicket
                        .getStationIdFrom() + " or e.stationId=" + stationsAndTimeForTicket.getStationIdTo() + ")" +
                        "and e.routeGroupId=" + i);
        return query.getResultList();
    }


    public List<ScheduleDetailsEntity> getStationsGroupByLinesId(int groupId, int lineIdFrom, int lineIdTo) {
        ScheduleDetailsEntity entityFrom = get(lineIdFrom);
        ScheduleDetailsEntity entityTo = get(lineIdTo);
        Query query = entityManager.createQuery(
                "SELECT e from ScheduleDetailsEntity e where e.routeGroupId=" + groupId + " and e.stationOrder>=" + entityFrom
                        .getStationOrder() + " and e.stationOrder<=" + entityTo.getStationOrder());
        return query.getResultList();
    }

    public ScheduleDetailsEntity update(ScheduleDetailsEntity scheduleDetailsEntity) {
        entityManager.getTransaction().begin();
        entityManager.merge(scheduleDetailsEntity);
        entityManager.getTransaction().commit();
        return scheduleDetailsEntity;
    }

    @Override
    public ScheduleDetailsEntity get(int id) {
        return entityManager.find(ScheduleDetailsEntity.class, id);
    }

//    @Override
//    public List<ScheduleDetailsEntity> getAll() {
//        Query query = entityManager.createQuery("SELECT e FROM TrainHasScheduleAndRouteEntity e");
//        return query.getResultList();
//    }

    @Override
    public List<ScheduleDetailsEntity> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM ScheduleDetailsEntity e");
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
