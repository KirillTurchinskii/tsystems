package com.turchinsky.service;

import com.turchinsky.dao.TrainHasScheduleAndRouteDao;
import com.turchinsky.entities.TrainHasScheduleAndRouteEntity;
import com.turchinsky.entities.TrainHasScheduleAndRouteEntityPK;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainHasScheduleAndRouteService
        implements ExtendedCRUDService<TrainHasScheduleAndRouteEntity, TrainHasScheduleAndRouteEntityPK> {

    private final TrainHasScheduleAndRouteDao trainHasScheduleAndRouteDao;

    private final RouteService routeService;

    private final TrainsService trainsService;

    public TrainHasScheduleAndRouteService(TrainHasScheduleAndRouteDao trainHasScheduleAndRouteDao,
                                           RouteService routeService, TrainsService trainsService) {
        this.trainHasScheduleAndRouteDao = trainHasScheduleAndRouteDao;
        this.routeService = routeService;
        this.trainsService = trainsService;
    }

    public TrainHasScheduleAndRouteEntity update(TrainHasScheduleAndRouteEntity trainHasScheduleAndRouteEntity) {
        return null;
    }

    @Override
    public TrainHasScheduleAndRouteEntity get(TrainHasScheduleAndRouteEntityPK trainHasScheduleAndRouteEntityPK) {
        return trainHasScheduleAndRouteDao.get(trainHasScheduleAndRouteEntityPK);
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
        TrainHasScheduleAndRouteEntityPK trainHasScheduleAndRouteEntityPK =
                new TrainHasScheduleAndRouteEntityPK(trainHasScheduleAndRouteEntity);

        TrainHasScheduleAndRouteEntity managedEntity = get(trainHasScheduleAndRouteEntityPK);
        trainHasScheduleAndRouteDao.delete(managedEntity);
    }

}
