package com.turchinsky.service;

import com.turchinsky.dao.TicketsDao;
import com.turchinsky.entities.ScheduleDetailsEntity;
import com.turchinsky.entities.TicketEntity;
import com.turchinsky.entities.TicketHolderEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService implements DefaultCRUDService<TicketEntity> {

    private final TicketsDao ticketsDao;

    private final ScheduleDetailsService scheduleDetailsService;

    private final TicketHolderService ticketHolderService;

    public TicketService(TicketsDao ticketsDao, ScheduleDetailsService scheduleDetailsService,
                         TicketHolderService ticketHolderService) {
        this.ticketsDao = ticketsDao;
        this.scheduleDetailsService = scheduleDetailsService;
        this.ticketHolderService = ticketHolderService;
    }

    public void buyTicket(int groupId, int lineIdFrom, int lineIdTo, int id) {
        List<ScheduleDetailsEntity> stationsGroupByLinesId =
                scheduleDetailsService.getStationsGroupByLinesId(groupId, lineIdFrom, lineIdTo);
        for (ScheduleDetailsEntity scheduleDetailsEntity :
                stationsGroupByLinesId) {
            scheduleDetailsEntity.setFreeSeats(scheduleDetailsEntity.getFreeSeats() - 1);
            scheduleDetailsEntity.setOrderedSeats(scheduleDetailsEntity.getOrderedSeats() + 1);
            scheduleDetailsService.update(scheduleDetailsEntity);
        }
        TicketEntity ticketEntity = new TicketEntity(20, scheduleDetailsService.get(lineIdFrom).getStationId(),
                                                     scheduleDetailsService.get(lineIdTo).getStationId(), lineIdFrom,
                                                     lineIdTo, id, groupId);
        save(ticketEntity);
    }

//    public List<ScheduleDetailsEntity> getByStationsAndTime(StationsAndTimeForTicket stationsAndTimeForTicket) {
//        return scheduleDetailsService.getByStationsAndTime(stationsAndTimeForTicket);
//    }

    public List<TicketHolderEntity> getHoldersByRouteGroupId(int groupId) {
        List<Integer> idByRouteGroup = getHoldersIdByRouteGroup(groupId);
        return ticketHolderService.getHoldersById(idByRouteGroup);
    }

    public List<Integer> getHoldersIdByRouteGroup(int routeGroupId) {
        return ticketsDao.getAllHoldersByGroupId(routeGroupId);
    }

    public boolean isUnique(int groupId, TicketHolderEntity ticketHolderEntity) {
        List<TicketHolderEntity> holdersByRouteGroupId = getHoldersByRouteGroupId(groupId);
        for (TicketHolderEntity holderEntity :
                holdersByRouteGroupId) {
            if (holderEntity.getName().equals(ticketHolderEntity.getName()) && holderEntity.getSurname()
                    .equals(ticketHolderEntity.getSurname()) && holderEntity.getBirthDate()
                    .equals(ticketHolderEntity.getBirthDate())) {
                return false;
            }
        }
        return true;
    }

    public List<TicketEntity> getByRouteGroupId(int routeGroupId) {
        return ticketsDao.getByRouteGroupId(routeGroupId);
    }

    @Override public TicketEntity get(int id) {
        return ticketsDao.get(id);
    }

    @Override public List<TicketEntity> getAll() {
        return ticketsDao.getAll();
    }

    @Override public TicketEntity save(TicketEntity ticketEntity) {
        return ticketsDao.save(ticketEntity);
    }

    @Override public void delete(TicketEntity ticketEntity) {
        TicketEntity merged = get(ticketEntity.getId());
        ticketsDao.delete(merged);
    }

}
