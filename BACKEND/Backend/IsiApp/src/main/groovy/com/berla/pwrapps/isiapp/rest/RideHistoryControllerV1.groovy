package com.berla.pwrapps.isiapp.rest


import com.berla.pwrapps.isiapp.model.Ride
import com.berla.pwrapps.isiapp.service.RideService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
//@CrossOrigin("*")
@RestController
@RequestMapping(value = "/rideHistory/")
public class RideHistoryControllerV1 {

    private final RideService rideService;

    @Autowired
    public RideHistoryControllerV1(RideService rideService) {
        this.rideService = rideService;
    }

    @GetMapping("/getForClient")
    public ResponseEntity<?> get(@RequestParam("id") Long id) {
        List<Ride> ridesForClient = rideService.findByClientId(id);
//        RideDtoWithoutLinks rideDto = rideService.findById(id);
        if(!ridesForClient.isEmpty())
            return ResponseEntity.ok(ridesForClient);
        else
            return ResponseEntity.ok("Ride not found");
    }

//    @GetMapping("/getAll")
//    public ResponseEntity<List<Ride>> getRides() {
//        List<Ride> list = rideService.findAll();
//        return ResponseEntity.ok(list);
//    }
//
//    @GetMapping("/getCost")
//    public ResponseEntity<GetCostReturnDto> getCost(@RequestParam("distance") Double distance) {
//        GetCostReturnDto getCostReturnDto = new GetCostReturnDto(rideService.getCost(distance));
//        ResponseEntity.ok(getCostReturnDto)
//    }
}
