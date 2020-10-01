package com.turchinsky.controllers;


import com.turchinsky.entities.StationEntity;
import com.turchinsky.service.StationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/stations")
public class StationsController {

    private final StationService stationService;

    public StationsController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("stationEntities", stationService.getAllStations());
        return "stations/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("stationEntity", stationService.getStationEntity(id));
        return "stations/show";
    }

    @GetMapping("/new-station")
    public String newStation(Model model) {
        model.addAttribute("station", new StationEntity());
        return "stations/new-station";
    }

    @PostMapping()
    public String create(@ModelAttribute("station") StationEntity stationEntity) {
        if (stationEntity.getName() != null && !stationEntity.getName().equals("") && stationService
                .checkNameIdentity(stationEntity.getName())) {
            stationService.save(stationEntity);
            return "redirect:/stations";
        } else {
            return "redirect:/stations/new-station";
        }
    }

    @GetMapping("/{id}/update")
    public String changeStation(@PathVariable("id") int id, Model model) {
        model.addAttribute("stationEntity", stationService.getStationEntity(id));
        return "stations/update-station";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("stationEntity") StationEntity stationEntity) {
        if (stationEntity.getName() != null && !stationEntity.getName().equals("") && stationService
                .checkNameIdentity(stationEntity.getName())) {
            stationService.save(stationEntity);
            return "redirect:/stations/" + stationEntity.getId();
        } else {
            return "redirect:/stations/" + stationEntity.getId() + "/update";
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteStation(@PathVariable("id") int id, Model model) {
        model.addAttribute("stationEntity", stationService.getStationEntity(id));
        return "stations/delete-alert";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("trainEntity") StationEntity stationEntity) {
        stationService.deleteStation(stationEntity);
        return "redirect:/stations";
    }

}
