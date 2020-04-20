package com.berla.pwrapps.isiapp.repository;

import com.berla.pwrapps.isiapp.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

}
