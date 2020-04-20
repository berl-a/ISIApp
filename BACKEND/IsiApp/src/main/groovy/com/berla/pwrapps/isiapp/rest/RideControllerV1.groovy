package com.berla.pwrapps.isiapp.rest


import com.berla.pwrapps.isiapp.dto.GetCostDto
import com.berla.pwrapps.isiapp.dto.GetCostReturnDto
import com.berla.pwrapps.isiapp.dto.RideDtoWithoutLinks
import com.berla.pwrapps.isiapp.model.Ride
import com.berla.pwrapps.isiapp.service.RideService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import javax.validation.Valid

//@CrossOrigin("*")
@RestController
@RequestMapping(value = "/ride/")
public class RideControllerV1 {

    private final RideService rideService;

    @Autowired
    public RideControllerV1(RideService rideService) {
        this.rideService = rideService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addRide(@RequestBody RideDtoWithoutLinks ride) {
        Long addedRideId = rideService.save(ride);
        return ResponseEntity.ok("Ride added with id = " + addedRideId);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> delete(@RequestParam("id") Long id) {
        rideService.deleteById(id);
        return ResponseEntity.ok("Ride deleted");
    }

    @PutMapping("/edit")
    public ResponseEntity<?> update(@RequestParam("id") Long id, @RequestBody RideDtoWithoutLinks user) {
        rideService.update(id, user);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body("Ride updated successfully");
    }

    @GetMapping("/get")
    public ResponseEntity<?> get(@RequestParam("id") Long id) {
        RideDtoWithoutLinks userDto = rideService.findById(id);
        if(userDto != null)
            return ResponseEntity.ok(userDto);
        else
            return ResponseEntity.ok("Ride not found");
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Ride>> getRides() {
        List<Ride> list = rideService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getCost")
    public ResponseEntity<GetCostReturnDto> getCost(@RequestBody @Valid GetCostDto getCostDao) {
        GetCostReturnDto getCostReturnDto = rideService.getCost(getCostDao);
        ResponseEntity.ok(getCostReturnDto)
    }
}
