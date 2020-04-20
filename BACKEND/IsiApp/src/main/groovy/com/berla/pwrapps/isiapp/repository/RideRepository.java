package com.berla.pwrapps.isiapp.repository;

import com.berla.pwrapps.isiapp.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {

}
