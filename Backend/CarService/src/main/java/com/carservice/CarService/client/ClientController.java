package com.carservice.CarService.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/clients")
public class ClientController {
    private final ClientService clientService;
    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") Long clientId) {
        Client client = clientService.getClientById(clientId);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> createClient(@RequestBody Client client) {
        Long savedClientId = clientService.createClient(client);
        return new ResponseEntity<>(savedClientId, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<HttpStatus> updateClient(
            @PathVariable("id") Long clientId,
            @RequestBody Client client
    ) {
        clientService.updateClient(clientId, client);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable("id") Long clientId) {
        clientService.deleteClient(clientId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
