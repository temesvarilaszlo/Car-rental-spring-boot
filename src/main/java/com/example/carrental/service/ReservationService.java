package com.example.carrental.service;

import com.example.carrental.model.Reservation;
import com.example.carrental.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation createReservation(Reservation reservation){
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getReservations(){
        return reservationRepository.findAll();
    }
}
