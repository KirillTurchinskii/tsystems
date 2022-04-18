package com.turchinsky.service;

import com.turchinsky.dao.TrainHasScheduleAndRouteDao;
import com.turchinsky.entities.TrainHasScheduleAndRouteEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrainHasScheduleAndRouteService
        implements DefaultCRUDService<TrainHasScheduleAndRouteEntity> {

    private final TrainHasScheduleAndRouteDao trainHasScheduleAndRouteDao;

    private final RouteService routeService;

    private final TrainsService trainsService;

    private final ScheduleDetailsService scheduleDetailsService;

    public TrainHasScheduleAndRouteService(TrainHasScheduleAndRouteDao trainHasScheduleAndRouteDao,
                                           RouteService routeService, TrainsService trainsService,
                                           ScheduleDetailsService scheduleDetailsService) {
        this.trainHasScheduleAndRouteDao = trainHasScheduleAndRouteDao;
        this.routeService = routeService;
        this.trainsService = trainsService;
        this.scheduleDetailsService = scheduleDetailsService;
    }

    public TrainHasScheduleAndRouteEntity update(TrainHasScheduleAndRouteEntity trainHasScheduleAndRouteEntity) {
        return null;
    }

    public TrainHasScheduleAndRouteEntity getByTrainRoteDepartureTime(int trainId, int routeId,
                                                                      Timestamp departureTime) {
        return trainHasScheduleAndRouteDao.getByTrainRoteDepartureTime(trainId, routeId, departureTime);
    }


    public boolean isTicketBoughtOnThisRouteGroupId(int roteGroupId) {
        return scheduleDetailsService.isTicketBoughtOnThisRouteGroupId(roteGroupId);
    }

    public List<TrainHasScheduleAndRouteEntity> getStartedByRoutesGroup() {
        List<Integer> usedGroups = scheduleDetailsService.getUsedGroups();
        List<TrainHasScheduleAndRouteEntity> trainHasScheduleAndRouteEntities = new ArrayList<>();
        for (Integer i :
                usedGroups) {
            trainHasScheduleAndRouteEntities.add(get(i));
        }
        return trainHasScheduleAndRouteEntities;
    }

    public boolean isTrainInitialized(int groupId) {
        return scheduleDetailsService.isTrainInitialized(groupId);
    }

    @Override public TrainHasScheduleAndRouteEntity get(int id) {
        return trainHasScheduleAndRouteDao.get(id);
    }

    @Override
    public List<TrainHasScheduleAndRouteEntity> getAll() {
        return trainHasScheduleAndRouteDao.getAll();
    }

    @Override
    public TrainHasScheduleAndRouteEntity save(TrainHasScheduleAndRouteEntity trainHasScheduleAndRouteEntity) {
        return trainHasScheduleAndRouteDao.save(trainHasScheduleAndRouteEntity);
    }

    @Override
    public void delete(TrainHasScheduleAndRouteEntity trainHasScheduleAndRouteEntity) {
        TrainHasScheduleAndRouteEntity managedEntity = get(trainHasScheduleAndRouteEntity.getRouteGroupId());
        scheduleDetailsService.deleteScheduleForGroupId(managedEntity.getRouteGroupId());
        trainHasScheduleAndRouteDao.delete(managedEntity);
    }

}
