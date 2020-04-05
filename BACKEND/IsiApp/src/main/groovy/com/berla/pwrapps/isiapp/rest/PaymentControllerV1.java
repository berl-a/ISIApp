package com.berla.pwrapps.isiapp.rest;

import com.berla.pwrapps.isiapp.dto.ClientDto;
import com.berla.pwrapps.isiapp.model.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/payments/")
public class PaymentControllerV1 {

    @PostMapping("/add")
    public ResponseEntity<String> addClient(@RequestBody ClientDto user) {
        Long addedId = 0L;
        return ResponseEntity.ok("Added with id = " + addedId);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> delete(@RequestParam("id") Long id) {
        return ResponseEntity.ok("Deleted");
    }

    @PutMapping("/edit")
    public ResponseEntity<?> update(@RequestParam("id") Long id, @RequestBody ClientDto user) {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body("Client updated successfully");
    }

    @GetMapping("/get")
    public ResponseEntity<?> get(@RequestParam("id") Long id) {
        HashMap<String, String> resObj = new HashMap<>();
        resObj.put("firstName", "FirstName");
        return ResponseEntity.ok(resObj);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Client>> getClients() {
        return ResponseEntity.ok(new LinkedList<>());
    }
}
