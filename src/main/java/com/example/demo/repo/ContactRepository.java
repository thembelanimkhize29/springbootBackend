package com.example.demo.repo;

import com.example.demo.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    //Contact findByEmail(String email);

    // Count the number of linked clients for a contact by contact email
    @Query("SELECT COUNT(c) FROM Contact ct JOIN ct.clients c WHERE ct.email = :email")
    int countClientsLinkedToContact(@Param("email") String email);

    Optional<Contact> findByEmail(String email);
}

