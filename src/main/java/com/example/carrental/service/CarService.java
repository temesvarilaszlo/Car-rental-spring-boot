package com.example.carrental.service;

import com.example.carrental.model.Car;
import com.example.carrental.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
