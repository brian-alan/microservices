package com.microservices.paymentservice.controller;

import com.microservices.paymentservice.dto.CardDTO;
import com.microservices.paymentservice.entity.Card;
import com.microservices.paymentservice.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private CardService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CardDTO>> getAll(){
        List<CardDTO> cards = service.getAllCards();
        return ResponseEntity.ok(cards);
    }

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CardDTO>> getCardsFromClient(@PathVariable(value = "userId") String userId){
        List<CardDTO> cards = service.getCardsFromClient(userId);
        return ResponseEntity.ok(cards);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CardDTO> saveCard(@RequestBody Card card){
        CardDTO newCard = service.saveCard(card);
        return ResponseEntity.ok(newCard);
    }
}
