package com.berla.pwrapps.isiapp.repository;

import com.berla.pwrapps.isiapp.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

}
