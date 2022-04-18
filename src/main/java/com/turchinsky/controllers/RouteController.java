package com.turchinsky.controllers;


import com.turchinsky.entities.RouteEntity;
import com.turchinsky.service.RouteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("routeEntities", routeService.getAllRouteEntities());
        return "routes/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("routeEntity", routeService.getRouteEntityById(id));
        return "routes/show";
    }

    @GetMapping("/new-route")
    public String newRoute(Model model) {
        RouteEntity routeEntity = new RouteEntity(0,"");
        model.addAttribute("route", routeEntity);
        return "routes/new-route";
    }

    @PostMapping()
    public String create(@ModelAttribute("route") RouteEntity routeEntity) {
        if (routeEntity.getName() != null && !routeEntity.getName().equals("") && routeService
                .checkRouteEntityNameIdentity(routeEntity.getName())) {
            routeService.saveRouteEntity(routeEntity);
            return "redirect:/routes";
        } else {
            return "redirect:/routes/new-route";
        }
    }

    @GetMapping("/{id}/update")
    public String changeRoute(@PathVariable("id") int id, Model model) {
        model.addAttribute("routeEntity", routeService.getRouteEntityById(id));
        return "routes/update-route";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("routeEntity") RouteEntity routeEntity) {
        if (routeEntity.getName() != null && !routeEntity.getName().equals("") && routeService
                .checkRouteEntityNameIdentity(routeEntity.getName())) {
            routeService.updateRouteEntity(routeEntity);
            return "redirect:/routes/" + routeEntity.getId();
        } else {
            return "redirect:/routes/" + routeEntity.getId() + "/update";
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteRoute(@PathVariable("id") int id, Model model) {
        model.addAttribute("routeEntity", routeService.getRouteEntityById(id));
        return "routes/delete-alert";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("routeEntity") RouteEntity routeEntity) {
        routeService.deleteRouteEntity(routeEntity);
        return "redirect:/routes";
    }


}
