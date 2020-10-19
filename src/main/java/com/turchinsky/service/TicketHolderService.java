package com.turchinsky.service;

import com.turchinsky.dao.TicketHolderDao;
import com.turchinsky.entities.TicketHolderEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketHolderService implements DefaultCRUDService<TicketHolderEntity> {

    private final TicketHolderDao ticketHolderDao;

    public TicketHolderService(TicketHolderDao ticketHolderDao) {
        this.ticketHolderDao = ticketHolderDao;
    }

    public boolean isUnique(String name, String surname, Date birthdate) {
        return ticketHolderDao.isUnique(name, surname, birthdate);
    }

    public TicketHolderEntity save(String name, String surname, Date birthdate) {
        TicketHolderEntity ticketHolderEntity = new TicketHolderEntity(name, surname, birthdate);
        return save(ticketHolderEntity);
    }


    public List<TicketHolderEntity> getHoldersById(List<Integer> idByRouteGroup) {
        List<TicketHolderEntity> ticketHolderEntities = new ArrayList<>();
        for (Integer integer :
                idByRouteGroup) {
            ticketHolderEntities.add(get(integer));
        }
        return ticketHolderEntities;
    }

    private TicketHolderEntity getTicketHolderEntityByData(TicketHolderEntity ticketHolderEntity) {
        return ticketHolderDao.getByData(ticketHolderEntity);
    }

    @Override
    public List<TicketHolderEntity> getAll() {
        return ticketHolderDao.getAll();
    }

    @Override
    public TicketHolderEntity get(int id) {
        return ticketHolderDao.get(id);
    }

    @Override
    public TicketHolderEntity save(TicketHolderEntity ticketHolderEntity) {
        if (isUnique(ticketHolderEntity.getName(), ticketHolderEntity.getSurname(),
                     ticketHolderEntity.getBirthDate())) {
            return ticketHolderDao.save(ticketHolderEntity);
        } else {
            TicketHolderEntity foundEntity = getTicketHolderEntityByData(ticketHolderEntity);
            return foundEntity;
        }

    }

    @Override
    public void delete(TicketHolderEntity passengerEntity) {
        TicketHolderEntity managedEntity = get(passengerEntity.getId());
        ticketHolderDao.delete(managedEntity);
    }

}
