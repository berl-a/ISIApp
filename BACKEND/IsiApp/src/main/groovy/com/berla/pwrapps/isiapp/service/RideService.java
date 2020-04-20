package com.berla.pwrapps.isiapp.service;

import com.berla.pwrapps.isiapp.dto.GetCostDto;
import com.berla.pwrapps.isiapp.dto.GetCostReturnDto;
import com.berla.pwrapps.isiapp.dto.RideDtoWithoutLinks;
import com.berla.pwrapps.isiapp.model.Ride;

import java.util.List;

/**
 * Interface for a ride service
 */

public interface RideService {

    Long save(Ride ride);

    Long save(RideDtoWithoutLinks rideDto);

    RideDtoWithoutLinks findById(long id);

    List<Ride> findAll();

    void deleteById(long id);

    void update(Long id, RideDtoWithoutLinks ride);

    GetCostReturnDto getCost(GetCostDto getCostDto);
}
