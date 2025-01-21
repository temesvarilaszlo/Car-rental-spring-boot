package com.example.CarRental.repository;

import com.example.CarRental.model.Car;
import com.example.CarRental.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {

}
