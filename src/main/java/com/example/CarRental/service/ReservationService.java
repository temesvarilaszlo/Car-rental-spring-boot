package com.example.CarRental.service;

import com.example.CarRental.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ReservationService {
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }
}
