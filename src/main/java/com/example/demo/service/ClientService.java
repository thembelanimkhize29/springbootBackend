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
import java.util.Set;

@Service
public class ClientService {


    private final ClientRepository clientRepository;
    private final ContactRepository contactRepository;

    public ClientService(ClientRepository clientRepository,ContactRepository contactRepository) {
        this.clientRepository = clientRepository;
        this.contactRepository= contactRepository;
    }

    // Method to generate the client code
    public String generateClientCode(String name) {
        String alphaPart = generateAlphaPart(name);
        int numericPart = 1; // Start with 001

        // Loop to find a unique client code
        String clientCode;
        do {
            clientCode = alphaPart + String.format("%03d", numericPart);
            numericPart++;
        } while (clientRepository.existsByClientCode(clientCode)); // Check uniqueness

        return clientCode;
    }

    // Method to generate the alpha part of the code
    private String generateAlphaPart(String name) {
        name = name.replaceAll("[^A-Z]", "").toUpperCase(); // Remove non-alphabet characters and convert to uppercase
        if (name.length() < 3) {
            return String.format("%-3s", name).replace(' ', 'A'); // Pad with 'A' if less than 3 characters
        } else {
            return name.substring(0, 3);
        }
    }
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

    // Method to count linked contacts with flexible client name matching
    public int countLinkedContacts(String clientName) {
        // Process the input if necessary (e.g., trim, lowercase)
        String processedName = clientName.trim();

        // Use repository method for counting
        return clientRepository.countContactsLinkedToClient(processedName);
    }
    public List<Client> getAllClientsOrderedByName() {
        return clientRepository.findAllByOrderByFirstNameAsc();
    }
}

