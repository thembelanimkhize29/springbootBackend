package com.example.demo.repo;

import com.example.demo.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findAllByOrderByFirstNameAsc();

    int countByClientCodeStartingWith(String alphaPart);

    boolean existsByClientCode(String clientCode);


    // Improved query: Using LIKE with case-insensitivity to handle variations
    @Query("SELECT c FROM Client c WHERE LOWER(TRIM(c.firstName)) LIKE LOWER(TRIM(:firstName))")
    Client findBySimilarFirstName(@Param("firstName") String firstName);

    // Count the number of linked contacts for a client using case-insensitive search
    @Query("SELECT COUNT(c) FROM Client cl JOIN cl.contacts c WHERE LOWER(TRIM(cl.firstName)) LIKE LOWER(TRIM(:firstName))")
    int countContactsLinkedToClient(@Param("firstName") String firstName);

    Optional<Client> findByFirstName(String firstName);
}
