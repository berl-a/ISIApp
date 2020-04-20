package com.berla.pwrapps.isiapp.rest;

import com.berla.pwrapps.isiapp.dto.CarDto;
import com.berla.pwrapps.isiapp.model.Car;
import com.berla.pwrapps.isiapp.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/car/")
public class CarControllerV1 {

    private final CarService carService;

    @Autowired
    public CarControllerV1(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCar(@RequestBody CarDto car) {
        Long addedCarId = carService.save(car);
        return ResponseEntity.ok("Car added with id = " + addedCarId);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> delete(@RequestParam("id") Long id) {
        carService.deleteById(id);
        return ResponseEntity.ok("Car deleted");
    }

    @PutMapping("/edit")
    public ResponseEntity<?> update(@RequestParam("id") Long id, @RequestBody CarDto car) {
        carService.update(id, car);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body("Car updated successfully");
    }

    @GetMapping("/get")
    public ResponseEntity<?> get(@RequestParam("id") Long id) {
        CarDto carDto = carService.findByIdDto(id);
        if(carDto != null)
            return ResponseEntity.ok(carDto);
        else
            return ResponseEntity.ok("Car not found");
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Car>> getCars() {
        List<Car> list = carService.findAll();
        return ResponseEntity.ok(list);
    }   
}
