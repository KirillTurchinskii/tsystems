package com.turchinsky.service;

import com.turchinsky.dao.PassengersDao;
import com.turchinsky.entities.PassengerEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService/* implements DefaultCRUDService<PassengerEntity> */{

    private final PassengersDao passengersDao;

    public PassengerService(PassengersDao passengersDao) {
        this.passengersDao = passengersDao;
    }

//    @Override
    public List<PassengerEntity> getAllPassengersEntities() {
        return passengersDao.getAll();
    }

//    @Override
    public PassengerEntity getPassengerEntityById(int id) {
        return passengersDao.get(id);
    }

//    @Override
    public PassengerEntity savePassengerEntity(PassengerEntity passengerEntity) {
        return passengersDao.save(passengerEntity);
    }

//    @Override
    public PassengerEntity updatePassengerEntity(PassengerEntity passengerEntity) {
        return passengersDao.update(passengerEntity);
    }


//    @Override
    public void deletePassengerEntity(PassengerEntity passengerEntity) {
        PassengerEntity managedEntity = getPassengerEntityById(passengerEntity.getId());
        passengersDao.delete(managedEntity);
    }

}
