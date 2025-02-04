package com.example.carrental.controller;

import com.example.carrental.exception.CustomValidationException;
import com.example.carrental.model.Car;
import com.example.carrental.model.Reservation;
import com.example.carrental.model.ReservationDTO;
import com.example.carrental.service.CarService;
import com.example.carrental.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @GetMapping("/create")
    public String createReservation(){
        return "reservation/reservation_form";
    }

    @PostMapping
    public String storeReservation(@Valid @ModelAttribute ReservationDTO reservationRequest, RedirectAttributes redirectAttributes){
        Map<String, String> errors = new HashMap<>();
        Optional<Car> reservedCar = carService.getCarById(reservationRequest.getCarId());
        if (reservedCar.isEmpty()){
            errors.put("otherError", "No such car.");
            throw new CustomValidationException(errors);
        }

        if (!reservedCar.get().isFree(reservationRequest.getStartDate(), reservationRequest.getEndDate())){
            errors.put("otherError", "Car is not free.");
            throw new CustomValidationException(errors);
        }

        Reservation reservation = reservationRequest.toReservation(reservedCar.get());
        Reservation r = reservationService.createReservation(reservation);

        redirectAttributes.addFlashAttribute("message",
                "Reservation from " + r.getStartDate().toString() + " to " + r.getEndDate().toString() +
                        " for car " + reservedCar.get().getName() + " was successfully created.");
        return "redirect:/";
    }


    @GetMapping("list")
    public ResponseEntity<List<Reservation>> getReservations(){
        return ResponseEntity.ok(reservationService.getReservations());
    }
}
