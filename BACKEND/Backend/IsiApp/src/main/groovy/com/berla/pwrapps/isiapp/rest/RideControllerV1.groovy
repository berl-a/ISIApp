package com.berla.pwrapps.isiapp.rest


import com.berla.pwrapps.isiapp.dto.GetCostReturnDto
import com.berla.pwrapps.isiapp.dto.RideDtoWithoutLinks
import com.berla.pwrapps.isiapp.model.Ride
import com.berla.pwrapps.isiapp.service.RideService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
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
    }//todo add linking ride to driver

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
    public ResponseEntity<GetCostReturnDto> getCost(@RequestParam("distance") Double distance) {
        GetCostReturnDto getCostReturnDto = new GetCostReturnDto(rideService.getCost(distance));
        ResponseEntity.ok(getCostReturnDto)
    }

    @PostMapping("/linkToDriver")
    public ResponseEntity<String> linkToDriver(@RequestParam("rideId") Long rideId, @RequestParam("driverId") Long driverId) {
        String result = rideService.linkToDriver(rideId, driverId);
        ResponseEntity.ok(result);
    }

    @PostMapping("/linkToClient")
    public ResponseEntity<String> linkToClient(@RequestParam("rideId") Long rideId, @RequestParam("clientId") Long clientId) {
        String result = rideService.linkToClient(rideId, clientId);
        ResponseEntity.ok(result);
    }
}
