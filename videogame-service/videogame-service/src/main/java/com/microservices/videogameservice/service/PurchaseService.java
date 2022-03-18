package com.microservices.videogameservice.service;

import com.microservices.videogameservice.entity.Purchase;
import com.microservices.videogameservice.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {
    @Autowired
    PurchaseRepository repository;

    public List<Purchase> getAllPurchases(){
        return repository.findAll();
    }

    public List<Purchase> getAllPurchasesFromUser(String userId){
        return repository.findPurchasesByUserId(userId);
    }

    public Purchase savePurchase(Purchase purchase){
        return repository.save(purchase);
    }
}
