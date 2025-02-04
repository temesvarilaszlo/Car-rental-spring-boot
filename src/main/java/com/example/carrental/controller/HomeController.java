package com.example.carrental.controller;

import com.example.carrental.config.UserRoleConfig;
import com.example.carrental.service.CarService;
import com.example.carrental.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final UserRoleConfig userRoleConfig;
    private final ReservationService reservationService;
    private final CarService carService;


    public HomeController(UserRoleConfig userRoleConfig, ReservationService reservationService, CarService carService) {
        this.userRoleConfig = userRoleConfig;
        this.reservationService = reservationService;
        this.carService = carService;
    }

    @GetMapping("/")
    public String hello(Model model){
        if (userRoleConfig.isAdmin()) return "redirect:/admin";

        model.addAttribute("title", "Home Page");
        return "index";
    }

    @GetMapping("/admin")
    public String admin(Model model){
        model.addAttribute("reservations", reservationService.getReservations());
        model.addAttribute("cars", carService.getCars());
        return "admin";
    }
}
