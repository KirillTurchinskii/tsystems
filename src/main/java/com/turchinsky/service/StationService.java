package com.turchinsky.service;


import com.turchinsky.dao.StationDao;
import com.turchinsky.entities.StationEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {

    private final StationDao stationDao;

    public StationService(StationDao stationDao) {
        this.stationDao = stationDao;
    }

    public List<StationEntity> getAllStations() {
        return stationDao.getAll();
    }

    public StationEntity save(StationEntity stationEntity) {
        return stationDao.save(stationEntity);
    }

    public void deleteStation(StationEntity stationEntity) {
        StationEntity managedEntity = getStationEntity(stationEntity.getId());
        stationDao.delete(managedEntity);
    }

    public StationEntity getStationEntity(int id) {
        return stationDao.get(id);
    }


    public boolean checkNameIdentity(String name) {
        List<StationEntity> byName = stationDao.getByName(name);
        return byName.size() == 0;
    }

}
