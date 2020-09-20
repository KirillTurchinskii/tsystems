package com.turchinsky.service;

import com.turchinsky.dao.TrainsDao;
import com.turchinsky.entities.TrainEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

//@Component(value = "trainsService")
//@Scope(value = "session")
@Service
public class TrainsService {

    private final TrainsDao trainsDao;

    public TrainsService(TrainsDao trainsDao) {
        this.trainsDao = trainsDao;
    }


    public List<TrainEntity> getAllTrains() {
        return trainsDao.getAll();
    }


    //
    public TrainEntity save(TrainEntity trainEntity) {
        return trainsDao.save(trainEntity);
//        return 0;
    }

    public void deleteTrain(TrainEntity trainEntity) {
        TrainEntity managedEntity = getTrainEntity(trainEntity.getId());
        trainsDao.delete(managedEntity);
    }

//    private void validate(TrainEntity trainEntity) {
//
//    }
//
    public TrainEntity getTrainEntity(int id) {
        return trainsDao.get(id);
    }
}
