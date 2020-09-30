package com.turchinsky.service;

import com.turchinsky.dao.RouteDao;
import com.turchinsky.entities.RouteEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

    private final RouteDao routeDao;

    public RouteService(RouteDao routeDao) {
        this.routeDao = routeDao;
    }

    public List<RouteEntity> getAllRoutes() {
        return routeDao.getAll();
    }

    public RouteEntity save(RouteEntity routeEntity) {
        return routeDao.save(routeEntity);
    }

    public void deleteRoute(RouteEntity routeEntity) {
        RouteEntity managedEntity = getRouteEntity(routeEntity.getId());
        routeDao.delete(managedEntity);
    }

    public RouteEntity getRouteEntity(int id) {
        return routeDao.get(id);
    }


}
