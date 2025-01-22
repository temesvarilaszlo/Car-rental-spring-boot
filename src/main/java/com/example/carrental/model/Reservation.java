package com.example.carrental.model;

import jakarta.persistence.*;

import java.time.ZonedDateTime;

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

    @Column(nullable = false)
    private String phoneNum;

    @Column(nullable = false)
    private ZonedDateTime beginning;

    @Column(nullable = false)
    private ZonedDateTime end;

    @Column(nullable = false)
    private int cost;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    public Reservation() {}

    public Reservation(String name, String email, String address, String phoneNum, ZonedDateTime beginning, ZonedDateTime end, Car car) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNum = phoneNum;
        this.beginning = beginning;
        this.end = end;
        this.car = car;
    }
}
