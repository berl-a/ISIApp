package com.berla.pwrapps.isiapp.repository;

import com.berla.pwrapps.isiapp.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
