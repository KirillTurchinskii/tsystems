package com.turchinsky.service;

import com.turchinsky.dao.RouteDao;
import com.turchinsky.entities.RouteEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService/* implements DefaultCRUDService<RouteEntity> */{

    private final RouteDao routeDao;

    public RouteService(RouteDao routeDao) {
        this.routeDao = routeDao;
    }

    public boolean checkRouteEntityNameIdentity(String name) {
        List<RouteEntity> byName = routeDao.getByName(name);
        return byName.size() == 0;
    }

    public RouteEntity updateRouteEntity(RouteEntity routeEntity) {
        return routeDao.update(routeEntity);
    }

    public List<RouteEntity> getAllRouteEntities() {
        return routeDao.getAll();
    }

    public RouteEntity saveRouteEntity(RouteEntity routeEntity) {
        return routeDao.save(routeEntity);
    }

    public void deleteRouteEntity(RouteEntity routeEntity) {
        RouteEntity managedEntity = getRouteEntityById(routeEntity.getId());
        routeDao.delete(managedEntity);
    }

    public RouteEntity getRouteEntityById(int id) {
        return routeDao.get(id);
    }


    // TODO: 10/9/2020 if there is no (tickets || trains) then delete all trains, all roite details and then delete route;

}
