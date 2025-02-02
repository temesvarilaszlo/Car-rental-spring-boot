package com.example.carrental.controller;

import com.example.carrental.model.Car;
import com.example.carrental.model.DateRangeDTO;
import com.example.carrental.service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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

    @PostMapping
    public ResponseEntity<String> createCar(@Valid @RequestBody Car carRequest){
        System.out.println(carRequest.isActive());
        Car car = new Car(carRequest.getName(), carRequest.getDailyCost());
        Car c = carService.createCar(car);
        return ResponseEntity.ok("ok" + c.getId());
    }
}
