package com.microservices.clientservice.repository;

import com.microservices.clientservice.entity.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ClientRepository extends MongoRepository<Client, Integer> {
    Optional<Client> findClientById(String id);
}
