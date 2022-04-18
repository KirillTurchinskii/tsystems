package com.turchinsky.controllers;

import com.turchinsky.entities.RouteDetailsEntity;
import com.turchinsky.entities.RouteDetailsEntityPK;
import com.turchinsky.entities.RouteEntity;
import com.turchinsky.entities.StationEntity;
import com.turchinsky.service.RouteDetailsService;
import com.turchinsky.service.RouteService;
import com.turchinsky.service.StationService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/route-details")
public class RouteDetailsController {

    private final RouteDetailsService routeDetailsService;

    private final RouteService routeService;

    private final StationService stationService;

    public RouteDetailsController(RouteDetailsService routeDetailsService, RouteService routeService,
                                  StationService stationService) {
        this.routeDetailsService = routeDetailsService;
        this.routeService = routeService;
        this.stationService = stationService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("routeDetailsEntities", routeDetailsService.getAllRouteDetailsEntities());
        return "routeDetails/index";
    }

    @GetMapping("/{routeId}/{stationId}/{stationOrder}")
    public String show(@PathVariable("routeId") int routeId, @PathVariable("stationId") int stationId,
                       @PathVariable("stationOrder") int stationOrder, Model model) {
        RouteDetailsEntityPK routeDetailsEntityPK = new RouteDetailsEntityPK(routeId, stationId, stationOrder);
        model.addAttribute("routeDetailsEntity", routeDetailsService.getRouteDetailsEntity(routeDetailsEntityPK));
        return "routeDetails/show";
    }

    @GetMapping("/{routeId}/{stationId}/{stationOrder}/update")
    public String updateKmType(@PathVariable("routeId") int routeId, @PathVariable("stationId") int stationId,
                               @PathVariable("stationOrder") int stationOrder, Model model) {
        RouteDetailsEntityPK routeDetailsEntityPK = new RouteDetailsEntityPK(routeId, stationId, stationOrder);
        RouteDetailsEntity routeDetailsEntity = routeDetailsService.getRouteDetailsEntity(routeDetailsEntityPK);
        model.addAttribute("routeDetailsEntity", routeDetailsEntity);

        model.addAttribute("routeName", routeService.getRouteEntityById(routeId).getName());
        model.addAttribute("stationName", stationService.get(stationId).getName());
        return "routeDetails/update-km-type";
    }

    @GetMapping("/new-route-details")
    public String newRouteDetails(Model model) {
        model.addAttribute("routeDetails", new RouteDetailsEntity());
        model.addAttribute("routeDetailsService", routeDetailsService);
        List<RouteEntity> routesList = routeDetailsService.getAllRoutes();
        List<StationEntity> stationsList = routeDetailsService.getAllStations();
        model.addAttribute("routesList", routesList);
        model.addAttribute("stationsList", stationsList);
        return "routeDetails/new-route-details";
    }

//    @PostMapping()
//    public String create(@ModelAttribute("routeDetails") RouteDetailsEntity routeDetailsEntity,
//                         @ModelAttribute("routesList") List<RouteEntity> routesList,
//                         @ModelAttribute("stationsList") List<StationEntity> stationsList) {
//        if (routeDetailsEntity.isPrimaryValuesNotZero(routeDetailsEntity) && routeDetailsService
//                .checkIdentity(routeDetailsEntity)) {
//            routeDetailsService.save(routeDetailsEntity);
//            return "redirect:/route-details";
//        } else {
//            return "redirect:/route-details/new-route-details";
//        }
//    }

    @GetMapping("/{route-id}/update")
    public String changeRouteDetails(@PathVariable("route-id") int routeId, Model model) {
        //RouteDetailsEntityPK entityPK = new RouteDetailsEntityPK(routeId, stationId, stationOrder);
        List<RouteEntity> routesList = routeDetailsService.getAllRoutes();
        List<StationEntity> stationsList = routeDetailsService.getAllStations();
        List<RouteDetailsEntity> routeDetailsByRouteId = routeDetailsService.getRouteDetailsByRouteId(routeId);
        List<StationEntity> freeStationsList = routeDetailsService.getFreeStations(routeId);
        model.addAttribute("freeStationsList", freeStationsList);
        model.addAttribute("currentRouteId", routeId);
        model.addAttribute("currentRouteName", routeService.getRouteEntityById(routeId).getName());
        model.addAttribute("routeDetailsEntities", routeDetailsByRouteId);
        model.addAttribute("routesList", routesList);
        model.addAttribute("stationsList", stationsList);
        return "routeDetails/update-route-details";
    }


    @GetMapping("/{route-id}/{km}/check-km")
    public @ResponseBody
    boolean validateNewKmInput(@PathVariable("route-id") int routeId, @PathVariable("km") int km) {
        return routeDetailsService.validateKm(routeId, km);
    }

    @GetMapping("/{route-id}/{station-order}/{km}/check-km")
    public @ResponseBody
    boolean validateKmBetweenStations(@PathVariable("route-id") int routeId,
                                      @PathVariable("station-order") int stationOrder, @PathVariable("km") int km) {
        return routeDetailsService.validateKm(routeId, stationOrder, km);
    }


    //    @ResponseBody
    @RequestMapping(value = "/add-route-details-part", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = {"Accept=application/json", "Content-Type=application/json"}/*, produces = MediaType.APPLICATION_JSON_VALUE*/)
    public @ResponseBody
    RouteDetailsEntity createRouteDetails(@RequestBody RouteDetailsEntity routeDetailsEntity) {
        System.out.println(routeDetailsEntity);
        routeDetailsService.setUp(routeDetailsEntity);
        routeDetailsService.saveRouteDetailsEntity(routeDetailsEntity);
        return routeDetailsEntity;
    }

    //    @GetMapping("/{route-id}/{station-id}/{station-order}/update")
//    public String changeRouteDetails(@PathVariable("route-id") int routeId,
//                                     @PathVariable("station-id") int stationId,
//                                     @PathVariable("station-order") int stationOrder, Model model) {
//        RouteDetailsEntityPK entityPK = new RouteDetailsEntityPK(routeId, stationId, stationOrder);
//        model.addAttribute("routeDetailsEntity", routeDetailsService.get(entityPK));
//        return "routeDetails/update-route-details";
//    }
//
    @RequestMapping(value = "/{route-id}/{station-id}/{station-order}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = {"Accept=application/json", "Content-Type=application/json"})
    public @ResponseBody
    boolean update(@RequestBody RouteDetailsEntity routeDetailsEntity) {
        RouteDetailsEntity detailsEntity = new RouteDetailsEntity();
        if (routeDetailsEntity.isPrimaryValuesNotZero(routeDetailsEntity) && routeDetailsService
                .checkIdentity(routeDetailsEntity)) {
            detailsEntity = routeDetailsService.saveRouteDetailsEntity(routeDetailsEntity);
        }

        return routeDetailsEntity.equals(detailsEntity);
    }

    @GetMapping("/{route-id}/{station-id}/{station-order}/delete")
    public String deleteRouteDetails(@PathVariable("route-id") int routeId,
                                     @PathVariable("station-id") int stationId,
                                     @PathVariable("station-order") int stationOrder, Model model) {
        RouteDetailsEntityPK entityPK = new RouteDetailsEntityPK(routeId, stationId, stationOrder);
        model.addAttribute("routeDetailsEntity", routeDetailsService.getRouteDetailsEntity(entityPK));
        model.addAttribute("routeName", routeService.getRouteEntityById(routeId).getName());
        model.addAttribute("stationName", stationService.get(stationId).getName());

        return "routeDetails/delete-alert";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("routeDetailsEntity") RouteDetailsEntity routeDetailsEntity) {
        routeDetailsService.deleteRouteDetailsEntity(routeDetailsEntity);
        return "redirect:/route-details/" + routeDetailsEntity.getRouteId() + "/update";
    }

}
