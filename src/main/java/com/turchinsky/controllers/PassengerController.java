package com.turchinsky.controllers;

import com.turchinsky.entities.PassengerEntity;
import com.turchinsky.service.PassengerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/passengers")
public class PassengerController {

    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("passengerEntities", passengerService.getAll());
        return "passengers/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("passengerEntity", passengerService.get(id));
        return "passengers/show";
    }

    @GetMapping("/new-passenger")
    public String newPassenger(Model model) {
        model.addAttribute("passenger", new PassengerEntity());
        return "passengers/new-passenger";
    }

    @PostMapping()
    public String create(@ModelAttribute("passenger") PassengerEntity passengerEntity) {
        if (!passengerEntity.getEmail()
                .equals("") && !passengerEntity.getName()
                .equals("") && !passengerEntity.getSurname().equals("") && !passengerEntity.getUsername().equals("")) {
            passengerService.save(passengerEntity);
            return "redirect:/passengers";
        } else {
            return "redirect:/passengers/new-passenger";
        }
    }

    @GetMapping("/{id}/update")
    public String changePassenger(@PathVariable("id") int id, Model model) {
        model.addAttribute("passengerEntity", passengerService.get(id));
        return "passengers/update-passenger";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("passengerEntity") PassengerEntity passengerEntity) {
        if (!passengerEntity.getEmail()
                .equals("") && !passengerEntity.getName()
                .equals("") && !passengerEntity.getSurname().equals("") && !passengerEntity.getUsername().equals("")) {
            passengerService.save(passengerEntity);
            return "redirect:/passengers/" + passengerEntity.getId();
        } else {
            return "redirect:/passengers/new-passenger/" + passengerEntity.getId() + "/update";
        }
        /// TODO: 10/9/2020 check fields before update. Empty string is valid in username?
    }

    @GetMapping("/{id}/delete")
    public String deletePassenger(@PathVariable("id") int id, Model model) {
        model.addAttribute("passengerEntity", passengerService.get(id));
        return "passengers/delete-alert";
    }

    public String delete(@ModelAttribute("passengerEntity") PassengerEntity passengerEntity) {
        passengerService.delete(passengerEntity);
        return "redirect:/passengers";
    }

}
