package com.turchinsky.service;

import com.turchinsky.dao.RouteDao;
import com.turchinsky.entities.RouteEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService implements DefaultCRUDService<RouteEntity> {

    private final RouteDao routeDao;

    public RouteService(RouteDao routeDao) {
        this.routeDao = routeDao;
    }

    public boolean checkNameIdentity(String name) {
        List<RouteEntity> byName = routeDao.getByName(name);
        return byName.size() == 0;
    }

    @Override
    public List<RouteEntity> getAll() {
        return routeDao.getAll();
    }

    @Override
    public RouteEntity save(RouteEntity routeEntity) {
        return routeDao.save(routeEntity);
    }

    @Override
    public void delete(RouteEntity routeEntity) {
        RouteEntity managedEntity = get(routeEntity.getId());
        routeDao.delete(managedEntity);
    }

    @Override
    public RouteEntity get(int id) {
        return routeDao.get(id);
    }


}
