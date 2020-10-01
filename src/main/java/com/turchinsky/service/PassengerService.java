package com.turchinsky.service;

import com.turchinsky.dao.PassengersDao;
import com.turchinsky.entities.PassengerEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {

    private final PassengersDao passengersDao;

    public PassengerService(PassengersDao passengersDao) {
        this.passengersDao = passengersDao;
    }

    public List<PassengerEntity> getAllPassengers() {
        return passengersDao.getAll();
    }

    public PassengerEntity getPassengerEntity(int id) {
        return passengersDao.get(id);
    }

    public PassengerEntity save(PassengerEntity passengerEntity) {
        return passengersDao.save(passengerEntity);
    }

    public void deletePassenger(PassengerEntity passengerEntity) {
        PassengerEntity managedEntity = getPassengerEntity(passengerEntity.getId());
        passengersDao.delete(managedEntity);
    }

}
