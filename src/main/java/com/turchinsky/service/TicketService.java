package com.turchinsky.service;

import com.turchinsky.dao.TicketsDao;
import com.turchinsky.entities.TicketEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService implements DefaultCRUDService<TicketEntity> {

    private final TicketsDao ticketsDao;

    private final ScheduleDetailsService scheduleDetailsService;

    public TicketService(TicketsDao ticketsDao, ScheduleDetailsService scheduleDetailsService) {
        this.ticketsDao = ticketsDao;
        this.scheduleDetailsService = scheduleDetailsService;
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

//    public List<ScheduleDetailsEntity> getByStationsAndTime(StationsAndTimeForTicket stationsAndTimeForTicket){
//        return scheduleDetailsService.getByStationsAndTime(stationsAndTimeForTicket);
//    }
}
