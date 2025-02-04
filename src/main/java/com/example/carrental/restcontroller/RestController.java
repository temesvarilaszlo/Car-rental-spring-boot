package com.example.carrental.restcontroller;

import com.example.carrental.exception.CustomValidationException;
import com.example.carrental.model.Car;
import com.example.carrental.model.DateRangeDTO;
import com.example.carrental.model.Reservation;
import com.example.carrental.model.ReservationDTO;
import com.example.carrental.service.CarService;
import com.example.carrental.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest-api")
public class RestController {
    private final CarService carService;
    private final ReservationService reservationService;

    @Autowired
    public RestController(CarService carService, ReservationService reservationService) {
        this.carService = carService;
        this.reservationService = reservationService;
    }

    @GetMapping("/cars/free")
    public ResponseEntity<List<Car>> getFreeCars(@Valid DateRangeDTO dateRangeDTO){
        return ResponseEntity.ok(carService.getFreeCars(dateRangeDTO.getStartDate(), dateRangeDTO.getEndDate()));
    }

    @PostMapping("/reservations")
    public ResponseEntity<Reservation> createReservation(@Valid @RequestBody ReservationDTO reservationRequest){
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

        if (!reservedCar.get().isActive()){
            errors.put("otherError", "Car is not active.");
            throw new CustomValidationException(errors);
        }

        Reservation reservation = reservationRequest.toReservation(reservedCar.get());
        Reservation r = reservationService.createReservation(reservation);

        return ResponseEntity.ok(r);
    }
}
