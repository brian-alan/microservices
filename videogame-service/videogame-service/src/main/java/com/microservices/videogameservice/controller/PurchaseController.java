package com.microservices.videogameservice.controller;

import com.microservices.videogameservice.entity.Purchase;
import com.microservices.videogameservice.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService service;

    @GetMapping
    public ResponseEntity<List<Purchase>> getAllPurchases(){
        List<Purchase> purchases = service.getAllPurchases();
        return ResponseEntity.ok(purchases);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Purchase>> getAllPurchasesFromUser(@PathVariable(value = "userId") String userId){
        List<Purchase> purchases = service.getAllPurchasesFromUser(userId);
        return ResponseEntity.ok(purchases);
    }

    @PostMapping
    public ResponseEntity<Purchase> saveNewPurchase(@RequestBody Purchase purchase){
        Purchase newPurchase = service.savePurchase(purchase);
        return ResponseEntity.ok(newPurchase);
    }

}
