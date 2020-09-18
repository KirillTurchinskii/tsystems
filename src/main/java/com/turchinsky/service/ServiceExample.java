//package com.turchinsky.service;
//
//import com.turchinsky.dao.Dao;
//import com.turchinsky.entities.TrainEntity;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import javax.persistence.PostLoad;
//import java.util.Collection;
//
//@Scope(value = "session")
//@Component(value = "trainsService")
//public class ServiceExample {
//
//    @Autowired
//    private Dao<TrainEntity> trainEntityDao;
//    private TrainEntity trainEntity = new TrainEntity();
//
//    @Autowired
//    public void setTrainEntityDao(Dao<TrainEntity> trainEntityDao) {
//        this.trainEntityDao = trainEntityDao;
//    }
//
//    public void save() {
//        trainEntityDao.save(trainEntity);
//        trainEntity = new TrainEntity();
//    }
//
//    public Collection<TrainEntity> getAllTrains() {
//        return trainEntityDao.getAll();
//    }
//
//    public int saveTrain(TrainEntity trainEntity) {
//        validate(trainEntity);
//        return trainEntityDao.save(trainEntity);
//    }
//
//    private void validate(TrainEntity trainEntity) {
//
//    }
//
//    public TrainEntity getTrainEntity() {
//        return trainEntity;
//    }
//}
