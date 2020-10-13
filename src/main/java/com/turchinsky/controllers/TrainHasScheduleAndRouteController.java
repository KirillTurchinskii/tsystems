package com.turchinsky.controllers;


import com.turchinsky.entities.TrainHasScheduleAndRouteEntity;
import com.turchinsky.entities.TrainHasScheduleAndRouteEntityPK;
import com.turchinsky.service.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.TimeZone;

@Controller
@RequestMapping("/train-schedule-route")
public class TrainHasScheduleAndRouteController {

    private final TrainHasScheduleAndRouteService trainHasScheduleAndRouteService;

    private final TrainsService trainsService;

    private final RouteService routeService;

    private final ScheduleDetailsService scheduleDetailsService;

    private final RouteDetailsService routeDetailsService;


    public TrainHasScheduleAndRouteController(
            TrainHasScheduleAndRouteService trainHasScheduleAndRouteService,
            TrainsService trainsService, RouteService routeService, ScheduleDetailsService scheduleDetailsService,
            RouteDetailsService routeDetailsService) {
        this.trainHasScheduleAndRouteService = trainHasScheduleAndRouteService;
        this.trainsService = trainsService;
        this.routeService = routeService;
        this.scheduleDetailsService = scheduleDetailsService;
        this.routeDetailsService = routeDetailsService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("trainScheduleRouteEntities", trainHasScheduleAndRouteService.getAll());
        model.addAttribute("routesList", routeService.getAll());
        model.addAttribute("trainsList", trainsService.getAll());
        return "trainScheduleRoute/index";
    }

    @GetMapping("/{trainId}/{roteId}/{departureTime}")
    public String show(@PathVariable("trainId") int trainId, @PathVariable("roteId") int roteId,
                       @PathVariable("departureTime") Timestamp departureTime, Model model) {

        TrainHasScheduleAndRouteEntity trainHasScheduleAndRouteEntity = trainHasScheduleAndRouteService
                .get(new TrainHasScheduleAndRouteEntityPK(trainId, roteId, departureTime));

        model.addAttribute("trainScheduleEntity", trainHasScheduleAndRouteEntity);
        model.addAttribute("routeEntity", routeService.get(roteId));
        model.addAttribute("trainEntity", trainsService.get(trainId));
        model.addAttribute("departureT", trainHasScheduleAndRouteEntity.getDepartureTime());
        return "trainScheduleRoute/show";
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
