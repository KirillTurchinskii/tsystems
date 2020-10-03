package com.turchinsky.service;


import com.turchinsky.dao.StationDao;
import com.turchinsky.entities.StationEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService implements DefaultCRUDService<StationEntity> {

    private final StationDao stationDao;

    public StationService(StationDao stationDao) {
        this.stationDao = stationDao;
    }

    public boolean checkNameIdentity(String name) {
        List<StationEntity> byName = stationDao.getByName(name);
        return byName.size() == 0;
    }

    @Override
    public List<StationEntity> getAll() {
        return stationDao.getAll();
    }

    @Override
    public StationEntity save(StationEntity stationEntity) {
        return stationDao.save(stationEntity);
    }

    @Override
    public void delete(StationEntity stationEntity) {
        StationEntity managedEntity = get(stationEntity.getId());
        stationDao.delete(managedEntity);
    }

    @Override
    public StationEntity get(int id) {
        return stationDao.get(id);
    }

}
