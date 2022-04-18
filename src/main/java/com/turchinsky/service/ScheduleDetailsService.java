package com.turchinsky.service;

import com.turchinsky.dao.ScheduleDetailsDao;
import com.turchinsky.entities.RouteDetailsEntity;
import com.turchinsky.entities.ScheduleDetailsEntity;
import com.turchinsky.entities.TrainEntity;
import com.turchinsky.entities.TrainHasScheduleAndRouteEntity;
import com.turchinsky.transfer.StationsAndTimeForTicket;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScheduleDetailsService implements DefaultCRUDService<ScheduleDetailsEntity> {


    private final ScheduleDetailsDao scheduleDetailsDao;

    private final RouteDetailsService routeDetailsService;

    private final TrainsService trainsService;

//    private final TrainHasScheduleAndRouteService trainHasScheduleAndRouteService;

    public ScheduleDetailsService(ScheduleDetailsDao scheduleDetailsDao,
                                  RouteDetailsService routeDetailsService,
                                  TrainsService trainsService) {
        this.scheduleDetailsDao = scheduleDetailsDao;
        this.routeDetailsService = routeDetailsService;
        this.trainsService = trainsService;
    }

    public void initialize(TrainHasScheduleAndRouteEntity trainHasScheduleAndRouteEntity) {
        List<RouteDetailsEntity> routeDetailsEntityList = routeDetailsService
                .getRouteDetailsByRouteId(trainHasScheduleAndRouteEntity.getRouteId());
        TrainEntity trainEntity = trainsService.getTrainEntity(trainHasScheduleAndRouteEntity.getTrainId());
        int capacity = trainEntity.getCapacity();
        double velocity = trainEntity.getVelocity();
        Timestamp prevDepartureTime = trainHasScheduleAndRouteEntity.getDepartureTime();
        int previousKm = 0;
        for (RouteDetailsEntity routeDetailsEntity : routeDetailsEntityList) {
            Timestamp arrivalTime = new Timestamp(0);
            Timestamp departureTime = new Timestamp(0);
            if (routeDetailsEntity.getStationOrder() == 1) {
                departureTime = trainHasScheduleAndRouteEntity.getDepartureTime();
                long stayTime = 10 * 1000 * 60;
                arrivalTime.setTime(departureTime.getTime() - stayTime);
            } else {
                int distance = Math.abs(routeDetailsEntity.getKm() - previousKm);
                double minutesToNextStation = (distance / velocity) * 60;
                int minute = 1000 * 60;
                long additionalTime = (long) (minutesToNextStation * minute);
                if (additionalTime < minute) {
                    additionalTime = minute;
                }
                int twoMinutes = 2 * 1000 * 60;
                long stayTime = twoMinutes;
                arrivalTime.setTime(prevDepartureTime.getTime() + additionalTime);
                departureTime.setTime(arrivalTime.getTime() + stayTime);
                prevDepartureTime = departureTime;
                previousKm = routeDetailsEntity.getKm();
            }

            ScheduleDetailsEntity scheduleDetailsEntity =
                    new ScheduleDetailsEntity(trainHasScheduleAndRouteEntity.getRouteGroupId(),
                                              routeDetailsEntity.getStationId(), routeDetailsEntity.getStationOrder(),
                                              trainHasScheduleAndRouteEntity
                                                      .getTrainId(), arrivalTime, departureTime, capacity, 0,
                                              trainHasScheduleAndRouteEntity.getRouteId());

            save(scheduleDetailsEntity);
        }
    }

//    public List<ScheduleDetailsEntity> getByStationsAndTime(StationsAndTimeForTicket stationsAndTimeForTicket) {
//        return scheduleDetailsDao.getByStationsAndTime(stationsAndTimeForTicket);
//
//    }

    public void deleteScheduleForGroupId(int routeGroupId) {
        List<ScheduleDetailsEntity> scheduleDetailsEntities = scheduleDetailsDao.getByRouteGroupId(routeGroupId);
        for (ScheduleDetailsEntity scheduleDetailsEntity :
                scheduleDetailsEntities) {
            delete(scheduleDetailsEntity);
        }
    }

    public List<Map<String, ScheduleDetailsEntity>> getPairFromToByRouteGroupId(
            StationsAndTimeForTicket stationsAndTimeForTicket) {
        List<Map<String, ScheduleDetailsEntity>> mapList = new ArrayList<>();
        List<Integer> routeGroups = getUsedGroups();
        Map<String, ScheduleDetailsEntity> scheduleDetailsEntityMap;
        for (int i :
                routeGroups) {
            List<ScheduleDetailsEntity> scheduleDetailsEntityList =
                    scheduleDetailsDao.getPairByGroupIdStationsAndTime(i, stationsAndTimeForTicket);

            if (scheduleDetailsEntityList.size() == 2) {
                scheduleDetailsEntityMap = getStationsPair(scheduleDetailsEntityList);
                System.out.println(scheduleDetailsEntityMap);
                mapList.add(scheduleDetailsEntityMap);
            }

        }
        return mapList;
    }

    public List<Map<String, ScheduleDetailsEntity>> getPairFromToByRouteGroupId(int stationFrom, int stationTo,
                                                                                Timestamp timeFrom, Timestamp timeTo) {
        StationsAndTimeForTicket stationsAndTimeForTicket =
                new StationsAndTimeForTicket(stationFrom, stationTo, timeFrom, timeTo);
        return getPairFromToByRouteGroupId(stationsAndTimeForTicket);
    }

    public int getMinAmountOfTicketsFromList(List<ScheduleDetailsEntity> scheduleDetailsEntities) {

        return scheduleDetailsEntities.stream().map(ScheduleDetailsEntity::getFreeSeats).min(Integer::compare).get();
    }

    public List<ScheduleDetailsEntity> getStationsGroupByLinesId(int groupId, int lineIdFrom, int lineIdTo) {
        return scheduleDetailsDao.getStationsGroupByLinesId(groupId, lineIdFrom, lineIdTo);
    }

    public boolean moreThanTenMinutesToDeparture(int lineIdFrom) {
        ScheduleDetailsEntity scheduleDetailsEntity = scheduleDetailsDao.get(lineIdFrom);
        long departureTime = scheduleDetailsEntity.getDepartureTime().getTime();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        long tenMinutes = 10 * 60 * 1000;
        int compare = Long.compare(departureTime - tenMinutes, currentTime.getTime());
        return compare > 0;
    }

    public ScheduleDetailsEntity update(ScheduleDetailsEntity scheduleDetailsEntity) {
        return scheduleDetailsDao.update(scheduleDetailsEntity);
    }

    public List<Integer> getUsedGroups() {
        return scheduleDetailsDao.getUsedGroups();
    }

    public boolean isTicketBought(int id) {
        return scheduleDetailsDao.isTicketBought(id);
    }

    public boolean isTicketBoughtOnThisRouteGroupId(int roteGroupId) {
        return scheduleDetailsDao.isTicketBoughtOnThisRouteGroupId(roteGroupId);
    }

    public boolean isTrainInitialized(int groupId) {
        return scheduleDetailsDao.isTrainInitialized(groupId);
    }

    private Map<String, ScheduleDetailsEntity> getStationsPair(List<ScheduleDetailsEntity> scheduleDetailsEntityList) {
        Map<String, ScheduleDetailsEntity> scheduleDetailsEntityMap = new HashMap<>();
        scheduleDetailsEntityMap.put("first", scheduleDetailsEntityList.get(0));
        scheduleDetailsEntityMap.put("second", scheduleDetailsEntityList.get(1));
        return scheduleDetailsEntityMap;
    }

    @Override
    public ScheduleDetailsEntity get(int id) {
        return scheduleDetailsDao.get(id);
    }

    @Override
    public List<ScheduleDetailsEntity> getAll() {
        return scheduleDetailsDao.getAll();
    }

    @Override
    public ScheduleDetailsEntity save(ScheduleDetailsEntity scheduleDetailsEntity) {
        return scheduleDetailsDao.save(scheduleDetailsEntity);
    }

    @Override
    public void delete(ScheduleDetailsEntity scheduleDetailsEntity) {
        ScheduleDetailsEntity managed = get(scheduleDetailsEntity.getLineId());
        scheduleDetailsDao.delete(managed);
    }

}
