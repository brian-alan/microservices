package com.microservices.videogameservice.repository;

import com.microservices.videogameservice.entity.Purchase;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PurchaseRepository extends MongoRepository<Purchase, String> {
    List<Purchase> findPurchasesByUserId(String userId);
}
