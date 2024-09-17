package com.example.demo.controller;

import com.example.demo.model.Client;
import com.example.demo.model.Contact;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/create")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        String clientCode = clientService.generateClientCode(client.getFirstName());
        client.setClientCode(clientCode);
        // Delegate the client creation to the service layer

        Client createdClient = clientService.createClient(client);

        // Return the created client and 201 status
        return ResponseEntity.status(201).body(createdClient);
    }
    @GetMapping("/list")
    public List<Client> getClients() {
        List<Client> clients = clientService.getAllClientsOrderedByName();
        if (clients.isEmpty()) {
            return null; // or handle it as you prefer
        }
        return clients;
    }
    @GetMapping("/count-linked-contacts")
    public int countLinkedContacts(@RequestParam String clientName) {
        // Call the service method to count linked contacts
        return clientService.countLinkedContacts(clientName);
    }
}

