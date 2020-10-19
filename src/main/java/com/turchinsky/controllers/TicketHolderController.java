package com.turchinsky.controllers;


import com.turchinsky.entities.ScheduleDetailsEntity;
import com.turchinsky.entities.TrainHasScheduleAndRouteEntity;
import com.turchinsky.service.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.TimeZone;

@Controller
@RequestMapping("/started-routes")
public class TicketHolderController {

    private final TrainHasScheduleAndRouteService trainHasScheduleAndRouteService;

    private final TrainsService trainsService;

    private final StationService stationService;

    private final RouteService routeService;

    private final ScheduleDetailsService scheduleDetailsService;

    private final RouteDetailsService routeDetailsService;

    private final TicketService ticketService;

    private final TicketHolderService ticketHolderService;

    public TicketHolderController(TrainHasScheduleAndRouteService trainHasScheduleAndRouteService,
                                  TrainsService trainsService, StationService stationService,
                                  RouteService routeService,
                                  ScheduleDetailsService scheduleDetailsService,
                                  RouteDetailsService routeDetailsService,
                                  TicketService ticketService,
                                  TicketHolderService ticketHolderService) {
        this.trainHasScheduleAndRouteService = trainHasScheduleAndRouteService;
        this.trainsService = trainsService;
        this.stationService = stationService;
        this.routeService = routeService;
        this.scheduleDetailsService = scheduleDetailsService;
        this.routeDetailsService = routeDetailsService;
        this.ticketService = ticketService;
        this.ticketHolderService = ticketHolderService;
    }

    @GetMapping
    public String index(Model model) {
        List<TrainHasScheduleAndRouteEntity> trainHasScheduleAndRouteEntities =
                trainHasScheduleAndRouteService.getStartedByRoutesGroup();
        model.addAttribute("trainScheduleRouteEntities", trainHasScheduleAndRouteService.getAll());
        model.addAttribute("routesList", routeService.getAll());
        model.addAttribute("trainsList", trainsService.getAll());
        return "ticketKeepper/indexTrainHasSchedule";
    }

    @GetMapping("/{routeGroupId}")
    public String show(@PathVariable int routeGroupId, Model model) {

        TrainHasScheduleAndRouteEntity trainHasScheduleAndRouteEntity = trainHasScheduleAndRouteService
                .get(routeGroupId);

        model.addAttribute("trainScheduleEntity", trainHasScheduleAndRouteEntity);
        model.addAttribute("routeEntity", routeService.get(trainHasScheduleAndRouteEntity.getRouteId()));
        model.addAttribute("trainEntity", trainsService.get(trainHasScheduleAndRouteEntity.getTrainId()));
        model.addAttribute("departureT", trainHasScheduleAndRouteEntity.getDepartureTime());
        model.addAttribute("ticketsList", ticketService.getByRouteGroupId(routeGroupId));
        model.addAttribute("passengersLst", ticketService.getHoldersByRouteGroupId(routeGroupId));
        model.addAttribute("trainsList", trainsService.getAll());
        model.addAttribute("stationsList", stationService.getAll());
        model.addAttribute("scheduleDetails", scheduleDetailsService.getAll());
        List<ScheduleDetailsEntity> all = scheduleDetailsService.getAll();
//        all.get(0).
        return "ticketKeepper/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("trainScheduleRouteEntities", trainHasScheduleAndRouteService.getAll());
        model.addAttribute("routesList", routeService.getAll());
        model.addAttribute("trainsList", trainsService.getAll());
        return "trainScheduleRoute/create";
    }

    @RequestMapping(value = "/start-train", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
                    headers = {"Accept=application/json", "Content-Type=application/json"})
    public @ResponseBody
    boolean startTrain(@RequestBody TrainHasScheduleAndRouteEntity trainHasScheduleAndRouteEntity) {
        scheduleDetailsService.initialize(trainHasScheduleAndRouteEntity);
        return true;
    }


//    @RequestMapping(value = "/stop-train", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
//                    headers = {"Accept=application/json", "Content-Type=application/json"})
//    public @ResponseBody
//    boolean stopTrain(@RequestBody TrainHasScheduleAndRouteEntity trainHasScheduleAndRouteEntity) {
//        scheduleDetailsService.deleteScheduleForGroupId(trainHasScheduleAndRouteEntity.getRouteGroupId());
//        return true;
//    }

//    @RequestMapping(value = "/check-time", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, headers = {"Accept=application/json", "Content-Type=application/json"})
//    public @ResponseBody
//    boolean checkTime(@RequestBody TrainHasScheduleAndRouteEntity trainHasScheduleAndRouteEntity) {
//
//        return false;
//    }

    @RequestMapping(value = "/create-train-schedule-route", method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    headers = {"Accept=application/json", "Content-Type=application/json"})
    public @ResponseBody
    TrainHasScheduleAndRouteEntity createTSR(
            @RequestBody TrainHasScheduleAndRouteEntity trainHasScheduleAndRouteEntity) {
        trainHasScheduleAndRouteEntity
                .setFreeSeats(trainsService.get(trainHasScheduleAndRouteEntity.getTrainId()).getCapacity());
        Timestamp departureTime = trainHasScheduleAndRouteEntity.getDepartureTime();
        System.out.println(departureTime);
        TimeZone timeZone = TimeZone.getDefault();
        long t = departureTime.getTime();
        departureTime.setTime(t - timeZone.getRawOffset());
        trainHasScheduleAndRouteService.save(trainHasScheduleAndRouteEntity);
        return trainHasScheduleAndRouteEntity;
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
                    headers = {"Accept=application/json", "Content-Type=application/json"})
    public void deleteTSR(
            @RequestBody TrainHasScheduleAndRouteEntity trainHasScheduleAndRouteEntity) {
        trainHasScheduleAndRouteService.delete(trainHasScheduleAndRouteEntity);
//        return "http://localhost:8080/train-schedule-route";
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
                    headers = {"Accept=application/json", "Content-Type=application/json"})
    public @ResponseBody
    TrainHasScheduleAndRouteEntity test(@RequestBody TrainHasScheduleAndRouteEntity trainHasScheduleAndRouteEntity
                                       ) {
        System.out.println(trainHasScheduleAndRouteEntity);

//        trainHasScheduleAndRouteService.delete(trainHasScheduleAndRouteEntity);
        return trainHasScheduleAndRouteEntity;
    }

}
