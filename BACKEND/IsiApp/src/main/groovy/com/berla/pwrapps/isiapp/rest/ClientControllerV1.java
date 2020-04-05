package com.berla.pwrapps.isiapp.rest;

import com.berla.pwrapps.isiapp.dto.ClientDto;
import com.berla.pwrapps.isiapp.model.Client;
import com.berla.pwrapps.isiapp.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/clients/")
public class ClientControllerV1 {

    private final ClientService clientService;

    @Autowired
    public ClientControllerV1(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addClient(@RequestBody ClientDto user) {
        Long addedClientId = clientService.save(user);
        return ResponseEntity.ok("Client added with id = " + addedClientId);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> delete(@RequestParam("id") Long id) {
        clientService.deleteById(id);
        return ResponseEntity.ok("Client deleted");
    }

    @PutMapping("/edit")
    public ResponseEntity<?> update(@RequestParam("id") Long id, @RequestBody ClientDto user) {
        clientService.update(id, user);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body("Client updated successfully");
    }

    @GetMapping("/get")
    public ResponseEntity<?> get(@RequestParam("id") Long id) {
        ClientDto userDto = clientService.findById(id);
        if(userDto != null)
            return ResponseEntity.ok(userDto);
        else
            return ResponseEntity.ok("Client not found");
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Client>> getClients() {
        List<Client> list = clientService.findAll();
        return ResponseEntity.ok(list);
    }
}
