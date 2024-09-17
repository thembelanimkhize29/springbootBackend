package com.example.demo.service;

import com.example.demo.model.Client;
import com.example.demo.model.Contact;
import com.example.demo.repo.ClientRepository;
import com.example.demo.repo.ContactRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ContactService {

    private final ClientRepository clientRepository;
    private final ContactRepository contactRepository;
    private final ClientService clientService;


    public ContactService(ClientRepository clientRepository,ContactRepository contactRepository,ClientService clientService) {
        this.clientRepository = clientRepository;
        this.contactRepository= contactRepository;
        this.clientService = clientService;

    }
    @Transactional
    public Contact createContact(Contact contact) {
        // Process each client in the contact's clients set
        Set<Client> uniqueClients = new HashSet<>();
        for (Client client : contact.getClients()) {
            // Check if the client already exists by firstName, otherwise create a new one
            Client existingClient = clientRepository.findByFirstName(client.getFirstName())
                    .orElseGet(() -> {
                        client.setClientCode(clientService.generateClientCode(client.getFirstName()));  // Set client code
                        return clientRepository.save(client);  // Save new client
                    });
            uniqueClients.add(existingClient);
        }

        // Set the updated unique clients in the contact
        contact.setClients(uniqueClients);

        // Save the contact with its clients
        return contactRepository.save(contact);
    }

//    @Transactional
//    public Contact createContact(Contact contact) {
//        return contactRepository.save(contact);
//    }
@Transactional
public Client createClient(Client client) {
    // Process each contact in the client's contacts set
    Set<Contact> uniqueContacts = new HashSet<>();
    for (Contact contact : client.getContacts()) {
        // Check if the contact already exists by email, otherwise create a new one
        Contact existingContact = contactRepository.findByEmail(contact.getEmail())
                .orElseGet(() -> contactRepository.save(contact)); // Save new contact if not found
        uniqueContacts.add(existingContact);
    }

    // Set the updated unique contacts in the client
    client.setContacts(uniqueContacts);

    // Save the client with its contacts
    return clientRepository.save(client);
}

    // Method to retrieve a contact by ID
    public Optional<Contact> getContact(Long contactId) {
        return contactRepository.findById(contactId);
    }

    // Method to list all contacts
    public List<Contact> listAllContacts() {
        return contactRepository.findAll();
    }

    // Method to get the number of clients linked to a specific contact
    public int countLinkedClients(String email) {
        // Directly use the repository method to count linked clients
        return contactRepository.countClientsLinkedToContact(email);
    }
}

