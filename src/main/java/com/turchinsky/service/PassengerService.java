package com.turchinsky.service;

import com.turchinsky.dao.PassengersDao;
import com.turchinsky.entities.PassengerEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService implements DefaultCRUDService<PassengerEntity> {

    private final PassengersDao passengersDao;

    public PassengerService(PassengersDao passengersDao) {
        this.passengersDao = passengersDao;
    }

    @Override
    public List<PassengerEntity> getAll() {
        return passengersDao.getAll();
    }

    @Override
    public PassengerEntity get(int id) {
        return passengersDao.get(id);
    }

    @Override
    public PassengerEntity save(PassengerEntity passengerEntity) {
        return passengersDao.save(passengerEntity);
    }

    @Override
    public void delete(PassengerEntity passengerEntity) {
        PassengerEntity managedEntity = get(passengerEntity.getId());
        passengersDao.delete(managedEntity);
    }

}
