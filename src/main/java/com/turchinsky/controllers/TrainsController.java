package com.turchinsky.controllers;

import com.turchinsky.entities.TrainEntity;
import com.turchinsky.service.TrainsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/trains")
public class TrainsController {


    private final TrainsService trainsService;

    public TrainsController(TrainsService trainsService) {
        this.trainsService = trainsService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("trainEntities", trainsService.getAllTrains());
        return "trains/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("trainEntity", trainsService.getTrainEntity(id));
        return "trains/show";
    }

    @GetMapping("/new-train")
    public String newTrain(Model model) {
        model.addAttribute("train", new TrainEntity());
        return "trains/new-train";
    }

    @PostMapping()
    public String create(@ModelAttribute("train") TrainEntity trainEntity) {
        if (trainEntity.getNumber() != null && trainEntity.getCapacity() != 0) {
            trainsService.save(trainEntity);
            return "redirect:/trains";
        } else {
            return "redirect:/trains/new-train";
        }
    }

    @GetMapping("/{id}/update")
    public String changeTrain(@PathVariable("id") int id, Model model) {
        model.addAttribute("trainEntity", trainsService.getTrainEntity(id));
        return "trains/update-train";
    }

    @PostMapping("/{id}")
        public String update(@ModelAttribute("trainEntity") TrainEntity trainEntity) {
        if (trainEntity.getNumber() != null && trainEntity.getCapacity() != 0) {
            trainsService.save(trainEntity);
            return "redirect:/trains/" + trainEntity.getId();
        } else {
            return "redirect:/trains/"+trainEntity.getId()+"/update";
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteTrain(@PathVariable("id")int id, Model model){
        model.addAttribute("trainEntity", trainsService.getTrainEntity(id));
        return "trains/delete-alert";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("trainEntity") TrainEntity trainEntity) {
        trainsService.deleteTrain(trainEntity);
        return "redirect:/trains";
    }

}
