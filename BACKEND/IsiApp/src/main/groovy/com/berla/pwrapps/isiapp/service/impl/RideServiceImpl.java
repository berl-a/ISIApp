package com.berla.pwrapps.isiapp.service.impl;

import com.berla.pwrapps.isiapp.dto.GetCostDto;
import com.berla.pwrapps.isiapp.dto.GetCostReturnDto;
import com.berla.pwrapps.isiapp.dto.RideDtoWithoutLinks;
import com.berla.pwrapps.isiapp.model.Ride;
import com.berla.pwrapps.isiapp.repository.RideRepository;
import com.berla.pwrapps.isiapp.service.GoogleMapsApiService;
import com.berla.pwrapps.isiapp.service.RideService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of a ride service
 */
@Service
public class RideServiceImpl implements RideService {

    private static final double PRICE_PER_KM = 1.2;

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(RideServiceImpl.class);

    @Autowired
    private GoogleMapsApiService googleMapsApiService;
    @Autowired
    private RideRepository rideRepository;

    @Override
    public Long save(Ride ride) {
        rideRepository.save(ride);
        log.info("IN RideServiceImpl: Saved ride with id = " + ride.getId());
        return ride.getId();
    }

    @Override
    public Long save(RideDtoWithoutLinks rideDto) {
        Ride newRide = new Ride(rideDto);
        rideRepository.save(newRide);
        log.info("IN RideServiceImpl: Saved ride from rideDto with id = " + newRide.getId());
        return newRide.getId();
    }

    @Override
    public RideDtoWithoutLinks findById(long id) {
        Ride foundRide = rideRepository.findById(id).orElse(null);
        log.info("IN RideServiceImpl: Found ride by id: " + foundRide);
        if(foundRide == null) {
            return null;
        } else {
            return foundRide.toRideDto();
        }
    }

    @Override
    public List<Ride> findAll() {
        log.info("IN RideServiceImpl: Getting all rides");
        return rideRepository.findAll();
    }

    @Override
    public synchronized void deleteById(long id) {
        log.info("IN RideServiceImpl: Deleting ride with id = " + id);
        rideRepository.deleteById(id);
        rideRepository.flush();
    }

    @Override
    public synchronized void update(Long id, RideDtoWithoutLinks rideWithNewInfo) {
        Ride foundRide = rideRepository.findById(id).orElse(null);
        if(foundRide != null) {
            foundRide.setOrigin(rideWithNewInfo.getOrigin());
            foundRide.setDestination(rideWithNewInfo.getDestination());
            foundRide.setPrice(rideWithNewInfo.getPrice());
            rideRepository.save(foundRide);
            rideRepository.flush();
            log.info("IN RideServiceImpl: Ride updated");
        }  else {
            log.info("IN RideServiceImpl: Ride not found during update");
        }
    }

    @Override
    public GetCostReturnDto getCost(GetCostDto getCostDto) {
        GetCostReturnDto costReturnDto = googleMapsApiService.getDistanceBetweenPoints(
                getCostDto.getOrigin(),
                getCostDto.getDestination()
        );
        if(costReturnDto != null) {
            int numberOfMeters = -1;
            if (costReturnDto.getDistance().contains("km")) {
                numberOfMeters = Integer.parseInt(costReturnDto.getDistance().split(" ")[0]) * 1000;
            } else if (costReturnDto.getDistance().contains("m")) {
                numberOfMeters = Integer.parseInt(costReturnDto.getDistance().split(" ")[0]);
            }
            double price = PRICE_PER_KM * ((double) numberOfMeters) / 1000.0;
            costReturnDto.setPrice(price);
            return costReturnDto;
        } else {
            GetCostReturnDto returnDto = new GetCostReturnDto();
            returnDto.setStatus("Error fetching information from Google Distance Matrix API");
            return returnDto;
        }
    }

}
