package com.example.carrental.model;

import com.example.carrental.validator.dateRange.ValidDateRange;
import com.example.carrental.validator.phoneNum.ValidPhoneNumber;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@ValidDateRange
public class ReservationDTO implements DateRange {
    private long id;

    @NotNull
    @Size(min = 1, max = 100)
    private String name;

    @NotNull
    @Size(min = 1, max = 100)
    @Email
    private String email;

    @NotNull
    @Size(min = 1, max = 255)
    private String address;

    @NotNull
    @Size(min = 1, max = 20)
    @ValidPhoneNumber
    private String phoneNum;

    @NotNull
    @FutureOrPresent
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull
    @FutureOrPresent
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @NotNull
    private long carId;

    public ReservationDTO(String name, String email, String address, String phoneNum, LocalDate startDate, LocalDate endDate, long carId) {
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

    @Override
    public LocalDate getStartDate() {
        return startDate;
    }

    @Override
    public LocalDate getEndDate() {
        return endDate;
    }
}
