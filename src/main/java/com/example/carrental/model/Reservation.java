package com.example.carrental.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, length = 20)
    private String phoneNum;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private long cost;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    @JsonBackReference
    private Car car;

    public Reservation() {}

    public Reservation(String name, String email, String address, String phoneNum, LocalDate startDate, LocalDate endDate, Car car) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNum = phoneNum;
        this.startDate = startDate;
        this.endDate = endDate;
        this.car = car;
        this.cost = (daysBetween(startDate, endDate) + 1) * car.getDailyCost();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public long getCost() {
        return cost;
    }

    public Car getCar() {
        return car;
    }

    private long daysBetween(LocalDate beginning, LocalDate end){
        return ChronoUnit.DAYS.between(beginning, end);
    }
}
