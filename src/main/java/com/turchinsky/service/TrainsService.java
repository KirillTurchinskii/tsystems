package com.turchinsky.service;

import com.turchinsky.dao.TrainsDao;
import com.turchinsky.entities.TrainEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TrainsService {

    private final TrainsDao trainsDao;

    public TrainsService(TrainsDao trainsDao) {
        this.trainsDao = trainsDao;
    }


    public List<TrainEntity> getAllTrains() {
        return trainsDao.getAll();
    }

    public TrainEntity save(TrainEntity trainEntity) {
        return trainsDao.save(trainEntity);
    }

    public void deleteTrain(TrainEntity trainEntity) {
        TrainEntity managedEntity = getTrainEntity(trainEntity.getId());
        trainsDao.delete(managedEntity);
    }

    public TrainEntity getTrainEntity(int id) {
        return trainsDao.get(id);
    }

    public boolean checkNumberIdentity(String number, TrainEntity trainEntity) {
        List<TrainEntity> byNumber = trainsDao.getByNumber(number);
        return (byNumber.size() == 0 || (byNumber.size() == 1 && byNumber.get(0).getId() == trainEntity.getId()));
    }

}
