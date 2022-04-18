package com.turchinsky.controllers;

import com.turchinsky.entities.ScheduleDetailsEntity;
import com.turchinsky.entities.TicketHolderEntity;
import com.turchinsky.service.*;
import com.turchinsky.transfer.TicketDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sbb")
public class TicketsController {

    private final StationService stationService;

    private final ScheduleDetailsService scheduleDetailsService;

    private final TicketHolderService ticketHolderService;

    private final TicketService ticketService;

    private final TrainsService trainsService;

    public TicketsController(StationService stationService,
                             ScheduleDetailsService scheduleDetailsService,
                             TicketHolderService ticketHolderService,
                             TicketService ticketService,
                             TrainsService trainsService) {
        this.stationService = stationService;
        this.scheduleDetailsService = scheduleDetailsService;
        this.ticketHolderService = ticketHolderService;
        this.ticketService = ticketService;
        this.trainsService = trainsService;
    }


    @GetMapping
    public String selectStations(Model model) {
        model.addAttribute("stationsList", stationService.getAll());
        return "ticket/select-stations";
    }


    @GetMapping(value = "/show-routes/{stationFrom}/{stationTo}/{timeFrom}/{timeTo}")
    public String searchForRoute(Model model, @PathVariable int stationFrom, @PathVariable int stationTo,
                                 @PathVariable Long timeFrom, @PathVariable Long timeTo) {
        Timestamp timestampFrom = new Timestamp(0);
        Timestamp timestampTo = new Timestamp(0);
        timestampFrom.setTime(timeFrom);
        timestampTo.setTime(timeTo);
        List<Map<String, ScheduleDetailsEntity>> scheduleDetailsGroup =
                scheduleDetailsService.getPairFromToByRouteGroupId(stationFrom, stationTo, timestampFrom, timestampTo);
        model.addAttribute("scheduleDetailsGroup", scheduleDetailsGroup);
        model.addAttribute("trainsList", trainsService.getAllTrainEntities());
        model.addAttribute("stationsList", stationService.getAll());
        return "ticket/select-train";
    }

    @GetMapping(value = "/selectTicket/{groupId}/{lineIdFrom}/{lineIdTo}")
    public String prePurchase(@PathVariable int groupId, @PathVariable int lineIdFrom, @PathVariable int lineIdTo,
                              Model model) {
        List<ScheduleDetailsEntity> stationsGroupByLinesId =
                scheduleDetailsService.getStationsGroupByLinesId(groupId, lineIdFrom, lineIdTo);
        int minAmmountOfTicketsOnRoute =
                scheduleDetailsService.getMinAmountOfTicketsFromList(stationsGroupByLinesId);
        model.addAttribute("ticketsLeft", minAmmountOfTicketsOnRoute);
        model.addAttribute("trainsList", trainsService.getAllTrainEntities());
        model.addAttribute("stationsList", stationService.getAll());
        model.addAttribute("stationFrom", scheduleDetailsService.get(lineIdFrom));
        model.addAttribute("stationTo", scheduleDetailsService.get(lineIdTo));
        return "ticket/select-ticket";
    }

    @GetMapping(value = "/registerUser/{groupId}/{lineIdFrom}/{lineIdTo}")
    public String registerUser(@PathVariable int groupId, @PathVariable int lineIdFrom,
                               @PathVariable int lineIdTo, Model model) {
        List<ScheduleDetailsEntity> stationsGroupByLinesId =
                scheduleDetailsService.getStationsGroupByLinesId(groupId, lineIdFrom, lineIdTo);
        int minAmountOfTicketsOnRoute =
                scheduleDetailsService.getMinAmountOfTicketsFromList(stationsGroupByLinesId);
        model.addAttribute("ticketsLeft", minAmountOfTicketsOnRoute);
        model.addAttribute("trainsList", trainsService.getAllTrainEntities());
        model.addAttribute("stationsList", stationService.getAll());
        model.addAttribute("stationFrom", scheduleDetailsService.get(lineIdFrom));
        model.addAttribute("stationTo", scheduleDetailsService.get(lineIdTo));
        model.addAttribute("ticketDetails", new TicketDetails());
        return "ticket/new-passenger";
    }

    @PostMapping(value = "/buy-ticket")
    public String buyTicket(@ModelAttribute("ticketDetails") TicketDetails ticketDetails) {
        synchronized (this) {

            List<ScheduleDetailsEntity> stationsGroupByLinesId =
                    scheduleDetailsService
                            .getStationsGroupByLinesId(ticketDetails.getGroupId(), ticketDetails.getLineIdFrom(),
                                                       ticketDetails.getLineIdTo());
            int minAmountOfTicketsOnRoute =
                    scheduleDetailsService.getMinAmountOfTicketsFromList(stationsGroupByLinesId);
            TicketHolderEntity ticketHolderEntity =
                    new TicketHolderEntity(ticketDetails.getName(), ticketDetails.getSurname(),
                                           ticketDetails.getBirthdate());
            if (minAmountOfTicketsOnRoute < 1 || !ticketService
                    .isUnique(ticketDetails.getGroupId(), ticketHolderEntity) || !scheduleDetailsService
                    .moreThanTenMinutesToDeparture(ticketDetails.getLineIdFrom())) {
                return "redirect:/sbb";
            } else {

                TicketHolderEntity savedTicketHolderEntity = ticketHolderService
                        .save(ticketHolderEntity);
                ticketService.buyTicket(ticketDetails.getGroupId(), ticketDetails.getLineIdFrom(),
                                        ticketDetails.getLineIdTo(), savedTicketHolderEntity.getId());
                return "redirect:/started-routes";
            }
        }

    }


//    @GetMapping(value = "/{routeGroup}/registered")
//    public String getPassengersByRouteGroup(@PathVariable String routeGroup){
//        return "ticket/registered-passengers";
//
//    }

}
