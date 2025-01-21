package com.example.CarRental.service;

import com.example.CarRental.model.Car;
import com.example.CarRental.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car createCar(Car car){
        return carRepository.save(car);
    }

    public List<Car> getCars(){
        return carRepository.findAll();
    }
}
