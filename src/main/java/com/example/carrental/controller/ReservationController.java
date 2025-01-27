package com.example.carrental.controller;

import com.example.carrental.model.Car;
import com.example.carrental.model.Reservation;
import com.example.carrental.model.ReservationDTO;
import com.example.carrental.service.CarService;
import com.example.carrental.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;
    private final CarService carService;

    @Autowired
    public ReservationController(ReservationService reservationService, CarService carService) {
        this.reservationService = reservationService;
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<String> createReservation(@RequestBody ReservationDTO reservationRequest){
        System.out.println(reservationRequest.getCarId());
        Optional<Car> reservedCar = carService.getCarById(reservationRequest.getCarId());
        if (reservedCar.isEmpty()) throw new RuntimeException("No such car");


        System.out.println(reservedCar.get().getReservations());
        Reservation reservation = reservationRequest.toReservation(reservedCar.get());
        Reservation r = reservationService.createReservation(reservation);
        return ResponseEntity.ok("ok" + r.getId());
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getReservations(){
        return ResponseEntity.ok(reservationService.getReservations());
    }
}
