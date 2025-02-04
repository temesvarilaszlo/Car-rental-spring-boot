package com.example.carrental.controller;

import com.example.carrental.model.Car;
import com.example.carrental.model.CarDTO;
import com.example.carrental.model.DateRangeDTO;
import com.example.carrental.service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<Car>> getCars(){
        return ResponseEntity.ok(carService.getCars());
    }

    @GetMapping("/free")
    public String getFreeCars(@Valid DateRangeDTO dateRangeDTO, Model model){
        LocalDate startDate = dateRangeDTO.getStartDate();
        LocalDate endDate = dateRangeDTO.getEndDate();

        List<Car> freeCars = carService.getFreeCars(startDate, endDate);
        model.addAttribute("cars", freeCars);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        return "car/cars";
    }

    @GetMapping("/create")
    public String createCar(){
        return "car/car_form";
    }

    @GetMapping("/{id}/edit")
    public String editCar(@PathVariable("id") Long id, Model model){
        Optional<Car> optionalCar = carService.getCarById(id);
        if (optionalCar.isEmpty()) return "error/404";

        model.addAttribute("car", optionalCar.get());

        return "car/car_form";
    }

    @PostMapping
    public String storeCar(@Valid @ModelAttribute CarDTO carRequest, RedirectAttributes redirectAttributes){
        Car car = carService.createCar(carRequest.toCar());
        redirectAttributes.addFlashAttribute("message", car.getName() + " was created.");

        return "redirect:/admin";
    }

    @PutMapping("/{id}")
    public String updateCar(@PathVariable("id") Long id, @Valid @ModelAttribute CarDTO carRequest, RedirectAttributes redirectAttributes){
        System.out.println("put request");
        try {
            Car car = carService.updateCar(id, carRequest.toCar());
            redirectAttributes.addFlashAttribute("message", car.getName() + " has been successfully updated.");
            return "redirect:/admin";
        }
        catch (RuntimeException e){
            return "error/404";
        }
    }
}
