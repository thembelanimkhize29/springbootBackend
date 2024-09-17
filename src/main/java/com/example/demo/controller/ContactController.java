package com.example.demo.controller;

import com.example.demo.model.Client;
import com.example.demo.model.Contact;
import com.example.demo.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/creat")
    public Contact createContact(@RequestBody Contact contact) {
        return contactService.createContact(contact);
    }
    @PostMapping("/create")
    public ResponseEntity<Contact> createContactp(@RequestBody Contact contact) {
        Contact c=contactService.createContact(contact);
        return ResponseEntity.status(201).body(c);
    }

    // Endpoint to get a specific contact
    @GetMapping("/{contactId}")
    public ResponseEntity<Contact> getContact(@PathVariable Long contactId) {
        return contactService.getContact(contactId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint to list all contacts
    @GetMapping("/all")
    public ResponseEntity<List<Contact>> getAllContacts() {
        List<Contact> contacts = contactService.listAllContacts();

        if (contacts.isEmpty()) {
            return ResponseEntity.noContent().build();  // Return 204 No Content if empty
        }

        return ResponseEntity.ok(contacts);  // Return 200 OK with the contact list
    }

    @GetMapping("/count-linked-clients")
    public int countLinkedClients(@RequestParam String email) {
        // Call the service method to count linked clients
        return contactService.countLinkedClients(email);
    }
}

