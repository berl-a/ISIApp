package com.berla.pwrapps.isiapp.rest;

import com.berla.pwrapps.isiapp.dto.DriverDto;
import com.berla.pwrapps.isiapp.model.Driver;
import com.berla.pwrapps.isiapp.service.DriverService;
import com.berla.pwrapps.isiapp.service.impl.DriverServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/driver/")
public class DriverControllerV1 {

    private final DriverService driverService;

    @Autowired
    public DriverControllerV1(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addDriver(@RequestBody DriverDto user) {
        Long addedDriverId = driverService.save(user);
        return ResponseEntity.ok("Driver added with id = " + addedDriverId);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> delete(@RequestParam("id") Long id) {
        driverService.deleteById(id);
        return ResponseEntity.ok("Driver deleted");
    }

    @PutMapping("/edit")
    public ResponseEntity<String> update(@RequestParam("id") Long id, @RequestBody DriverDto user) {
        driverService.update(id, user);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body("Driver updated successfully");
    }

    @GetMapping("/get")
    public ResponseEntity<?> get(@RequestParam("id") Long id) {
        DriverDto userDto = driverService.findByIdDto(id);
        if(userDto != null)
            return ResponseEntity.ok(userDto);
        else
            return ResponseEntity.ok("Driver not found");
    }

    @PutMapping("/addCarToDriver")
    public ResponseEntity<?> addCarToDriver(@RequestParam("driverId") Long driverId, @RequestParam("carId") Long carId) {
        String addingResult = driverService.addCarToDriver(driverId, carId);
        if(DriverServiceImpl.SUCCESS.equals(addingResult)) {
            return ResponseEntity.ok("Car added to driver");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(addingResult);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Driver>> getDrivers() {//todo maybe replace with DTO, in other controllers too
        List<Driver> list = driverService.findAll();
        return ResponseEntity.ok(list);
    }

    //add
}
