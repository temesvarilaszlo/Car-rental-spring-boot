package com.example.carrental.model;

import com.example.carrental.validator.integer.ValidInteger;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private Integer dailyCost;

    @Column(nullable = false)
    private boolean isActive;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id")
    @JsonManagedReference
    private List<Reservation> reservations;

    public Car() {}

    public Car(String name, int dailyCost) {
        this.name = name;
        this.dailyCost = dailyCost;
        this.isActive = true;
    }

    public boolean isFree(LocalDate startDate, LocalDate endDate){
        for (Reservation reservation : reservations) {
            if (!(endDate.isBefore(reservation.getStartDate()) || startDate.isAfter(reservation.getEndDate()))) {
                return false;
            }
        }
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDailyCost(int dailyCost) {
        this.dailyCost = dailyCost;
    }

    public String getName() {
        return name;
    }

    public int getDailyCost() {
        return dailyCost;
    }

    public boolean isActive() {
        return isActive;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
