package com.example.carrental.controller;

import com.example.carrental.model.Car;
import com.example.carrental.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.time.LocalDate;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest-api")
public class RestController {
    private final CarService carService;

    @Autowired
    public RestController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars/free")
    public ResponseEntity<List<Car>> getFreeCars(@RequestParam("start") LocalDate startDate, @RequestParam("end") LocalDate endDate){
        return ResponseEntity.ok(carService.getFreeCars(startDate, endDate));
    }
}
