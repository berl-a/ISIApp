package com.berla.pwrapps.isiapp.service.impl;

import com.berla.pwrapps.isiapp.dto.RideDtoWithoutLinks;
import com.berla.pwrapps.isiapp.model.Client;
import com.berla.pwrapps.isiapp.model.Driver;
import com.berla.pwrapps.isiapp.model.Ride;
import com.berla.pwrapps.isiapp.repository.RideRepository;
import com.berla.pwrapps.isiapp.service.ClientService;
import com.berla.pwrapps.isiapp.service.DriverService;
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
    @Autowired
    private DriverService driverService;
    @Autowired
    private ClientService clientService;

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
            foundRide.setOriginLat(rideWithNewInfo.getOriginLat());
            foundRide.setOriginLon(rideWithNewInfo.getOriginLon());
            foundRide.setDestinationLat(rideWithNewInfo.getDestinationLat());
            foundRide.setDestinationLon(rideWithNewInfo.getDestinationLon());
            foundRide.setPrice(rideWithNewInfo.getPrice());
            rideRepository.save(foundRide);
            rideRepository.flush();
            log.info("IN RideServiceImpl: Ride updated");
        }  else {
            log.info("IN RideServiceImpl: Ride not found during update");
        }
    }

    @Override
    public Double getCost(Double distance) {
        return distance * 1.34;
    }

    @Override
    public List<Ride> findByClientId(Long id) {
        return rideRepository.findByClient(id);
    }

    @Override
    public String linkToDriver(long rideId, long driverId) {
        Ride foundRide = rideRepository.findById(rideId).orElse(null);
        Driver foundDriver = driverService.findById(driverId);
        if(foundRide != null && foundDriver != null) {
            foundRide.setDriver(foundDriver);
            rideRepository.save(foundRide);
            rideRepository.flush();
            log.info("IN RideServiceImpl: Ride updated");
            return "SUCCESS";
        }  else {
            log.info("IN RideServiceImpl: Ride or driver not found during update");
            return "RIDE OR DRIVER NOT FOUND";
        }
    }

    @Override
    public String linkToClient(long rideId, long clientId) {
        Ride foundRide = rideRepository.findById(rideId).orElse(null);
        Client foundClient = clientService.findById(clientId);
        if(foundRide != null && foundClient != null) {
            foundRide.setClient(foundClient);
            rideRepository.save(foundRide);
            rideRepository.flush();
            log.info("IN RideServiceImpl: Ride updated");
            return "SUCCESS";
        }  else {
            log.info("IN RideServiceImpl: Ride or client not found during update");
            return "RIDE OR CLIENT NOT FOUND";
        }
    }

}
