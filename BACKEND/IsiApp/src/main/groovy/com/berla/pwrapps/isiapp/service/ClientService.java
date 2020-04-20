package com.berla.pwrapps.isiapp.service;

import com.berla.pwrapps.isiapp.dto.ClientDto;
import com.berla.pwrapps.isiapp.model.Client;

import java.util.List;

/**
 * Interface for a client service
 */
public interface ClientService {

    Long save(Client client);

    Long save(ClientDto clientDto);

    ClientDto findById(long id);

    List<Client> findAll();

    void deleteById(long id);

    void update(Long id, ClientDto client);
}
