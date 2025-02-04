package com.example.carrental.service;

import com.example.carrental.model.Car;
import com.example.carrental.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    public List<Car> getFreeCars(LocalDate startDate, LocalDate endDate){
        return carRepository.findAll().stream().filter(car -> car.isFree(startDate, endDate) && car.isActive()).toList();
    }

    public Optional<Car> getCarById(Long id){
        return carRepository.findById(id);
    }

    public Car updateCar(Long id, Car updatedCar) {
        return carRepository.findById(id).map(car -> {
            car.setId(id);
            car.setName(updatedCar.getName());
            car.setDailyCost(updatedCar.getDailyCost());
            return carRepository.save(car);
        }).orElseThrow(RuntimeException::new);
    }
}
