package com.berla.pwrapps.isiapp.service;

import com.berla.pwrapps.isiapp.dto.DriverDto;
import com.berla.pwrapps.isiapp.model.Driver;

import java.util.List;

/**
 * Interface for a driver service
 */

public interface DriverService {

    Long save(Driver driver);

    Long save(DriverDto driverDto);

    Driver findById(long id);

    DriverDto findByIdDto(long id);

    List<Driver> findAll();

    void deleteById(long id);

    void update(Long id, DriverDto driver);

    String addCarToDriver(long driverId, long carId);
}
