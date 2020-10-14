package com.turchinsky.controllers;

import com.turchinsky.service.PassengerService;
import com.turchinsky.service.ScheduleDetailsService;
import com.turchinsky.service.StationService;
import com.turchinsky.service.TicketService;
import com.turchinsky.transfer.StationsAndTimeForTicket;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sbb")
public class TicketsController {

    private final StationService stationService;

    private final ScheduleDetailsService scheduleDetailsService;

    private final PassengerService passengerService;

    private final TicketService ticketService;

    public TicketsController(StationService stationService,
                             ScheduleDetailsService scheduleDetailsService,
                             PassengerService passengerService, TicketService ticketService) {
        this.stationService = stationService;
        this.scheduleDetailsService = scheduleDetailsService;
        this.passengerService = passengerService;
        this.ticketService = ticketService;
    }


    @GetMapping
    public String selectStations(Model model) {
        model.addAttribute("stationsList", stationService.getAll());
        return "buyTicket/select-stations";
    }


    @RequestMapping(value = "/show-routes", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
                    headers = {"Accept=application/json", "Content-Type=application/json"})
    public @ResponseBody boolean searchForRoute(@RequestBody StationsAndTimeForTicket stationsAndTimeForTicket) {
        System.out.println(stationsAndTimeForTicket);

        return false;
    }

}
