package com.turchinsky.service;

import com.turchinsky.dao.TrainsDao;
import com.turchinsky.entities.TrainEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TrainsService/* implements DefaultCRUDService<TrainEntity>*/ {

    private final TrainsDao trainsDao;
    private ScheduleDetailsService scheduleDetailsService;

    public TrainsService(TrainsDao trainsDao) {
        this.trainsDao = trainsDao;
    }

    public void setScheduleDetailsService(ScheduleDetailsService scheduleDetailsService) {
        this.scheduleDetailsService = scheduleDetailsService;
    }

    public boolean checkNumberIdentity(String number, TrainEntity trainEntity) {
        List<TrainEntity> byNumber = trainsDao.getByNumber(number);
        return (byNumber.size() == 0 || (byNumber.size() == 1 && byNumber.get(0).getId() == trainEntity.getId()));
    }

    /*@Override*/
    public List<TrainEntity> getAllTrainEntities() {
        return trainsDao.getAll();
    }

    /*@Override*/
    public TrainEntity saveTrainEntity(TrainEntity trainEntity) {
        return trainsDao.save(trainEntity);
    }

    /*@Override*/
    public void deleteTrainEntity(TrainEntity trainEntity) {
        if (!scheduleDetailsService.isTicketBought(trainEntity.getId())){
            TrainEntity managedEntity = getTrainEntity(trainEntity.getId());
            trainsDao.delete(managedEntity);
        }

    }


    /*@Override*/
    public TrainEntity getTrainEntity(int id) {
        return trainsDao.get(id);
    }

}
