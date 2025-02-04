package com.example.carrental.model;

import com.example.carrental.validator.integer.ValidInteger;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CarDTO {
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String name;

    @NotNull
    @ValidInteger
    @Positive
    @Column(nullable = false)
    private String dailyCost;

    public CarDTO(String name, String dailyCost) {
        this.name = name;
        this.dailyCost = dailyCost;
    }

    public Car toCar(){
        return new Car(name, Integer.parseInt(dailyCost));
    }
}
