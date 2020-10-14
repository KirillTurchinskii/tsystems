package com.turchinsky.service;

import com.turchinsky.dao.ScheduleDetailsDao;
import com.turchinsky.entities.RouteDetailsEntity;
import com.turchinsky.entities.ScheduleDetailsEntity;
import com.turchinsky.entities.TrainEntity;
import com.turchinsky.entities.TrainHasScheduleAndRouteEntity;
import com.turchinsky.transfer.StationsAndTimeForTicket;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ScheduleDetailsService implements DefaultCRUDService<ScheduleDetailsEntity> {


    private final ScheduleDetailsDao scheduleDetailsDao;

    private final RouteDetailsService routeDetailsService;

    private final TrainsService trainsService;

    private final TrainHasScheduleAndRouteService trainHasScheduleAndRouteService;

    public ScheduleDetailsService(ScheduleDetailsDao scheduleDetailsDao,
                                  RouteDetailsService routeDetailsService,
                                  TrainsService trainsService,
                                  TrainHasScheduleAndRouteService trainHasScheduleAndRouteService) {
        this.scheduleDetailsDao = scheduleDetailsDao;
        this.routeDetailsService = routeDetailsService;
        this.trainsService = trainsService;
        this.trainHasScheduleAndRouteService = trainHasScheduleAndRouteService;
    }

    public void initialize(TrainHasScheduleAndRouteEntity trainHasScheduleAndRouteEntity) {
        List<RouteDetailsEntity> routeDetailsEntityList = routeDetailsService
                .getRouteDetailsByRouteId(trainHasScheduleAndRouteEntity.getRouteId());
        TrainEntity trainEntity = trainsService.get(trainHasScheduleAndRouteEntity.getTrainId());
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

    public List<ScheduleDetailsEntity> getByStationsAndTime(StationsAndTimeForTicket stationsAndTimeForTicket) {
        return scheduleDetailsDao.getByStationsAndTime(stationsAndTimeForTicket);

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
