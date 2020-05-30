package com.berla.pwrapps.isiapp.service.impl;

import com.berla.pwrapps.isiapp.dto.DriverDto;
import com.berla.pwrapps.isiapp.model.Car;
import com.berla.pwrapps.isiapp.model.Driver;
import com.berla.pwrapps.isiapp.repository.DriverRepository;
import com.berla.pwrapps.isiapp.service.CarService;
import com.berla.pwrapps.isiapp.service.DriverService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of a driver service
 */
@Service
public class DriverServiceImpl implements DriverService {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(DriverServiceImpl.class);
    public static final String SUCCESS = "Success";
    public static final String DRIVER_AND_CAR_NOT_FOUND = "Driver and car not found";
    public static final String DRIVER_NOT_FOUND = "Driver not found";
    public static final String CAR_NOT_FOUND = "Car not found";

    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private CarService carService;

    @Override
    public Long save(Driver driver) {
        driverRepository.save(driver);
        log.info("IN DriverServiceImpl: Saved driver with id = " + driver.getId());
        return driver.getId();
    }

    @Override
    public Long save(DriverDto driverDto) {
        Driver newDriver = new Driver(driverDto);
        driverRepository.save(newDriver);
        log.info("IN DriverServiceImpl: Saved driver from driverDto with id = " + newDriver.getId());
        return newDriver.getId();
    }

    @Override
    public Driver findById(long id) {
        return driverRepository.findById(id).orElse(null);
    }

    @Override
    public DriverDto findByIdDto(long id) {
        Driver foundDriver = driverRepository.findById(id).orElse(null);
        log.info("IN DriverServiceImpl: Found driver by id: " + foundDriver);
        if(foundDriver == null) {
            return null;
        } else {
            return foundDriver.toDriverDto();
        }
    }

    @Override
    public List<Driver> findAll() {
        log.info("IN DriverServiceImpl: Getting all drivers");
        return driverRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        log.info("IN DriverServiceImpl: Deleting driver with id = " + id);
        driverRepository.deleteById(id);
        driverRepository.flush();
    }

    @Override
    public void update(Long id, DriverDto driver) {

        Driver foundDriver = driverRepository.findById(id).orElse(null);
        if(foundDriver != null) {
            //todo add modification of driver
            driverRepository.save(foundDriver);
            driverRepository.flush();
            log.info("IN DriverServiceImpl: Driver updated");
        }  else {
            log.info("IN DriverServiceImpl: Driver not found during update");
        }
    }

    @Override
    public String addCarToDriver(long driverId, long carId) {
        Driver driver = driverRepository.getOne(driverId);
        Car car = carService.findById(carId);
        String addingResult = SUCCESS;
        if(driver == null && car == null) {
            log.error(DRIVER_AND_CAR_NOT_FOUND);
            addingResult = DRIVER_AND_CAR_NOT_FOUND;
        } else if(driver == null) {
            log.error(DRIVER_NOT_FOUND);
            addingResult = DRIVER_NOT_FOUND;
        } else if(car == null) {
            log.error(CAR_NOT_FOUND);
            addingResult = CAR_NOT_FOUND;
        } else {
            driver.setCar(car);
            car.setDriver(driver);

            save(driver);
            carService.save(car);
        }
        return addingResult;
    }

}
