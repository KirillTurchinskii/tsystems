package com.turchinsky.controllers;


import com.turchinsky.entities.TrainHasScheduleAndRouteEntity;
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
        model.addAttribute("routesList", routeService.getAllRouteEntities());
        model.addAttribute("trainsList", trainsService.getAllTrainEntities());
        return "trainScheduleRoute/index";
    }

    @GetMapping("/{trainId}/{roteId}/{departureTime}")
    public String show(@PathVariable("trainId") int trainId, @PathVariable("roteId") int roteId,
                       @PathVariable("departureTime") Timestamp departureTime, Model model) {

        TrainHasScheduleAndRouteEntity trainHasScheduleAndRouteEntity = trainHasScheduleAndRouteService
                .getByTrainRoteDepartureTime(trainId, roteId, departureTime);

        model.addAttribute("trainScheduleEntity", trainHasScheduleAndRouteEntity);
        model.addAttribute("routeEntity", routeService.getRouteEntityById(roteId));
        model.addAttribute("trainEntity", trainsService.getTrainEntity(trainId));
        model.addAttribute("departureT", trainHasScheduleAndRouteEntity.getDepartureTime());
        return "trainScheduleRoute/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("trainScheduleRouteEntities", trainHasScheduleAndRouteService.getAll());
        model.addAttribute("routesList", routeService.getAllRouteEntities());
        model.addAttribute("trainsList", trainsService.getAllTrainEntities());
        return "trainScheduleRoute/create";
    }

    @RequestMapping(value = "/start-train", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
                    headers = {"Accept=application/json", "Content-Type=application/json"})
    public @ResponseBody
    boolean startTrain(@RequestBody TrainHasScheduleAndRouteEntity trainHasScheduleAndRouteEntity) {
        scheduleDetailsService.initialize(trainHasScheduleAndRouteEntity);
        return true;
    }

    @RequestMapping(value = "check-tickets/{group-id}", method = RequestMethod.GET,
                    headers = {"Accept=application/json", "Content-Type=application/json"})
    public @ResponseBody boolean isTicketBoughtOnThisRouteGroupId(@PathVariable("group-id") int groupId) {
        return trainHasScheduleAndRouteService.isTicketBoughtOnThisRouteGroupId(groupId);
    }

    @RequestMapping(value = "check-group-id/{group-id}", method = RequestMethod.GET,
                    headers = {"Accept=application/json", "Content-Type=application/json"})
    public @ResponseBody boolean isTrainInitialized(@PathVariable("group-id") int groupId) {
        return trainHasScheduleAndRouteService.isTrainInitialized(groupId);
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
                .setFreeSeats(trainsService.getTrainEntity(trainHasScheduleAndRouteEntity.getTrainId()).getCapacity());
        Timestamp departureTime = trainHasScheduleAndRouteEntity.getDepartureTime();
        System.out.println(departureTime);
        TimeZone timeZone = TimeZone.getDefault();
        long t = departureTime.getTime();
        departureTime.setTime(t - timeZone.getRawOffset());
        trainHasScheduleAndRouteService.save(trainHasScheduleAndRouteEntity);
        return trainHasScheduleAndRouteEntity;
    }


    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE,
                    headers = {"Accept=application/json", "Content-Type=application/json"})
    public void deleteTSR(
            @RequestBody TrainHasScheduleAndRouteEntity trainHasScheduleAndRouteEntity) {
        trainHasScheduleAndRouteService.delete(trainHasScheduleAndRouteEntity);
//        return "http://localhost:8080/train-schedule-route";
    }

}
