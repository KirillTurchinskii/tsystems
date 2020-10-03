package com.turchinsky.service;

import com.turchinsky.dao.TrainsDao;
import com.turchinsky.entities.TrainEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TrainsService implements DefaultCRUDService<TrainEntity> {

    private final TrainsDao trainsDao;

    public TrainsService(TrainsDao trainsDao) {
        this.trainsDao = trainsDao;
    }

    public boolean checkNumberIdentity(String number, TrainEntity trainEntity) {
        List<TrainEntity> byNumber = trainsDao.getByNumber(number);
        return (byNumber.size() == 0 || (byNumber.size() == 1 && byNumber.get(0).getId() == trainEntity.getId()));
    }

    @Override
    public List<TrainEntity> getAll() {
        return trainsDao.getAll();
    }

    @Override
    public TrainEntity save(TrainEntity trainEntity) {
        return trainsDao.save(trainEntity);
    }

    @Override
    public void delete(TrainEntity trainEntity) {
        TrainEntity managedEntity = get(trainEntity.getId());
        trainsDao.delete(managedEntity);
    }

    @Override
    public TrainEntity get(int id) {
        return trainsDao.get(id);
    }

}
