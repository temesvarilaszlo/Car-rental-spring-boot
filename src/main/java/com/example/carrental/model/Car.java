package com.example.carrental.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name is required")
    @Length(min = 1, max = 100, message = "Name is required")
    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private boolean isActive;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id")
    @JsonManagedReference
    private List<Reservation> reservations;

    public Car() {}

    public Car(String name) {
        this.name = name;
        this.isActive = true;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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
