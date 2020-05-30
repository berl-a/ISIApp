package com.berla.pwrapps.isiapp.service.impl;

import com.berla.pwrapps.isiapp.dto.CarDto;
import com.berla.pwrapps.isiapp.model.Car;
import com.berla.pwrapps.isiapp.repository.CarRepository;
import com.berla.pwrapps.isiapp.service.CarService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of a car service
 */
@Service
public class CarServiceImpl implements CarService {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(CarServiceImpl.class);

    @Autowired
    private CarRepository carRepository;

    @Override
    public Long save(Car car) {
        carRepository.save(car);
        log.info("IN CarServiceImpl: Saved car with id = " + car.getId());
        return car.getId();
    }

    @Override
    public Long save(CarDto carDto) {
        Car newCar = new Car(carDto);
        carRepository.save(newCar);
        log.info("IN CarServiceImpl: Saved car from carDto with id = " + newCar.getId());
        return newCar.getId();
    }

    @Override
    public Car findById(long id) {
        return carRepository.findById(id).orElse(null);
    }

    @Override
    public CarDto findByIdDto(long id) {
        Car foundCar = carRepository.findById(id).orElse(null);
        log.info("IN CarServiceImpl: Found car by id: " + foundCar);
        if(foundCar == null) {
            return null;
        } else {
            return foundCar.toCarDto();
        }
    }

    @Override
    public List<Car> findAll() {
        log.info("IN CarServiceImpl: Getting all cars");
        return carRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        log.info("IN CarServiceImpl: Deleting car with id = " + id);
        carRepository.deleteById(id);
        carRepository.flush();
    }

    @Override
    public void update(Long id, CarDto car) {

        Car foundCar = carRepository.findById(id).orElse(null);
        if(foundCar != null) {
            foundCar.setBrand(car.getBrand());
            foundCar.setModel(car.getModel());
            carRepository.save(foundCar);
            carRepository.flush();
            log.info("IN CarServiceImpl: Car updated");
        }  else {
            log.info("IN CarServiceImpl: Car not found during update");
        }
    }

}
