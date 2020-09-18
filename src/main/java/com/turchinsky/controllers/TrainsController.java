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
//        for (TrainEntity trainEntity : trainsService.getAllTrains()) {
//            System.out.println(trainEntity);
//        }
        model.addAttribute("trainEntities", trainsService.getAllTrains());
        return "trains/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
//        TrainEntity entity = trainsService.getTrainEntity(id);
//        System.out.println("???");
//        System.out.println(entity);
        model.addAttribute("trainEntity", trainsService.getTrainEntity(id));
        return "trains/show";
    }

    @GetMapping("/new-train")
    public String newTrain(Model model) {
        model.addAttribute("train", new TrainEntity());
        return "trains/new-train";
    }

    @PostMapping
    public String create(@ModelAttribute("train") TrainEntity trainEntity) {
        if (trainEntity.getName() != null && trainEntity.getCapacity() != 0) {
            trainsService.save(trainEntity);
            /// TODO: 9/18/2020 Let TrainsService decide what input is valid and return int
            return "redirect:/trains";
        } else {
            return "redirect:/trains/new-train";
        }
    }

}
