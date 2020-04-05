package com.berla.pwrapps.isiapp.service.impl;

import com.berla.pwrapps.isiapp.dto.ClientDto;
import com.berla.pwrapps.isiapp.model.Client;
import com.berla.pwrapps.isiapp.repository.ClientRepository;
import com.berla.pwrapps.isiapp.service.ClientService;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of a client service
 */
@Service
public class ClientServiceImpl implements ClientService {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ClientServiceImpl.class);
    final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Long save(Client client) {
        clientRepository.save(client);
        log.info("IN ClientServiceImpl: Saved client with id = " + client.getId());
        return client.getId();
    }

    @Override
    public Long save(ClientDto clientDto) {
        Client newClient = new Client(clientDto);
        clientRepository.save(newClient);
        log.info("IN ClientServiceImpl: Saved client from clientDto with id = " + newClient.getId());
        return newClient.getId();
    }

    @Override
    public ClientDto findById(long id) {
        Client foundClient = clientRepository.findById(id).orElse(null);
        log.info("IN ClientServiceImpl: Found client by id: " + foundClient);
        if(foundClient == null) {
            return null;
        } else {
            return foundClient.toClientDto();
        }
    }

    @Override
    public List<Client> findAll() {
        log.info("IN ClientServiceImpl: Getting all clients");
        return clientRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        log.info("IN ClientServiceImpl: Deleting client with id = " + id);
        clientRepository.deleteById(id);
        clientRepository.flush();
    }

    @Override
    public void update(Long id, ClientDto client) {

        Client foundClient = clientRepository.findById(id).orElse(null);
        if(foundClient != null) {
            foundClient.setFirstName(client.getFirstName());
            foundClient.setLastName(client.getLastName());
            clientRepository.save(foundClient);
            clientRepository.flush();
            log.info("IN ClientServiceImpl: Client updated");
        }  else {
            log.info("IN ClientServiceImpl: Client not found during update");
        }
    }

}
