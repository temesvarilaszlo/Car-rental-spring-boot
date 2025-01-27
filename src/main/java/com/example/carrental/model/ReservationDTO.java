package com.example.carrental.model;

import java.time.LocalDate;

public class ReservationDTO {
    private long id;
    private String name;
    private String email;
    private String address;
    private String phoneNum;
    private LocalDate startDate;
    private LocalDate endDate;
    private long carId;

    public ReservationDTO(long id, String name, String email, String address, String phoneNum, LocalDate startDate, LocalDate endDate, long carId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNum = phoneNum;
        this.startDate = startDate;
        this.endDate = endDate;
        this.carId = carId;
    }

    public Reservation toReservation(Car car){
        return new Reservation(name, email, address, phoneNum, startDate, endDate, car);
    }

    public long getCarId() {
        return carId;
    }
}
