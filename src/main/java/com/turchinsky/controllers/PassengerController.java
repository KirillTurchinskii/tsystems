package com.turchinsky.controllers;

import com.turchinsky.entities.PassengerEntity;
import com.turchinsky.service.PassengerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Objects;

@Controller
@RequestMapping("/passengers")
public class PassengerController {

    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("passengerEntities", passengerService.getAllPassengersEntities());
        return "passengers/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("passengerEntity", passengerService.getPassengerEntityById(id));
        return "passengers/show";
    }

    @GetMapping("/new-passenger")
    public String newPassenger(Model model) {
        model.addAttribute("passenger", new PassengerEntity(0, "", "", new Date(0), "", ""));
        return "passengers/new-passenger";
    }

    @PostMapping()
    public String create(@ModelAttribute("passenger") PassengerEntity passengerEntity) {
        if (Objects.isNull(passengerEntity.getBirthDate())) {
            /// TODO: 10/20/2020 Throw validation exception
        }
        if (!passengerEntity.getEmail()
                .equals("") && !passengerEntity.getName()
                .equals("") && !passengerEntity.getSurname().equals("") && !passengerEntity.getUsername().equals("")) {
            passengerService.savePassengerEntity(passengerEntity);
            return "redirect:/passengers";
        } else {
            return "redirect:/passengers/new-passenger";
        }
    }

    @GetMapping("/{id}/update")
    public String changePassenger(@PathVariable("id") int id, Model model) {
        PassengerEntity passengerEntity = passengerService.getPassengerEntityById(id);
        if (passengerEntity.getId() != 0) {
            model.addAttribute("passengerEntity", passengerEntity);
            return "passengers/update-passenger";
        } else {
            return "passengers/index";
        }
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("passengerEntity") PassengerEntity passengerEntity) {
        if (Objects.isNull(passengerEntity.getBirthDate())) {
            /// TODO: 10/20/2020 Throw validation exception
        }
        if (!passengerEntity.getEmail()
                .equals("") && !passengerEntity.getName()
                .equals("") && !passengerEntity.getSurname().equals("") && !passengerEntity.getUsername().equals("")) {
            passengerService.updatePassengerEntity(passengerEntity);
            return "redirect:/passengers/" + passengerEntity.getId();
        } else {
            return "redirect:/passengers/new-passenger/" + passengerEntity.getId() + "/update";
        }
        /// TODO: 10/9/2020 check fields before update. Empty string is valid in username?
    }

    @GetMapping("/{id}/delete")
    public String deletePassenger(@PathVariable("id") int id, Model model) {
        PassengerEntity passengerEntity = passengerService.getPassengerEntityById(id);
        if (passengerEntity.getId() != 0) {
            model.addAttribute("passengerEntity", passengerEntity);
            return "passengers/delete-alert";
        } else {
            return "passengers/index";
        }
    }

    public String delete(@ModelAttribute("passengerEntity") PassengerEntity passengerEntity) {
        passengerService.deletePassengerEntity(passengerEntity);
        return "redirect:/passengers";
    }

}
