package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  //  {
//          name: "Jane",
//          surname: "Smith",
//          email: "thembelanimkhize29@gmail.com"
    //clients=[
  // "name":"Capitec"
  // ]
//     }
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "email", nullable = false)
    private String email;

    @ManyToMany(mappedBy = "contacts")
    @JsonIgnore //and Use DTOs (Data Transfer Objects) to avoid issues like
    // infinite recursion in JSON responses due to the bi-directional relationship.
    //Using a ContactDTO and ClientDTO helps to avoid exposing internal details
    // and handling the bi-directional relationship properly.
    //if a Contact contains Client, and Client contains Contact,
    // serializing this structure could lead to an infinite loop between the two entities.
    private Set<Client> clients = new HashSet<>();

    // Constructors, getters, and setters
    public Contact() {
    }

    public Contact(Long id, String name, String surname, String email, Set<Client> clients) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.clients = clients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }
}

