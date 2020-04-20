package com.berla.pwrapps.isiapp.service;

import com.berla.pwrapps.isiapp.dto.CarDto;
import com.berla.pwrapps.isiapp.model.Car;

import java.util.List;

/**
 * Interface for a car service
 */

public interface CarService {

    Long save(Car ride);

    Long save(CarDto rideDto);

    CarDto findByIdDto(long id);

    Car findById(long id);

    List<Car> findAll();

    void deleteById(long id);

    void update(Long id, CarDto ride);
}
