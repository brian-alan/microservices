package com.microservices.clientservice.controller;

import com.microservices.clientservice.dto.CardDTO;
import com.microservices.clientservice.dto.ClientDTO;
import com.microservices.clientservice.entity.Client;
import com.microservices.clientservice.model.Card;
import com.microservices.clientservice.utils.exceptions.ClientUnprocessableEntity;
import com.microservices.clientservice.service.ClientService;
import com.microservices.clientservice.validator.ClientValidator;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @Autowired
    private ClientValidator validator;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClientDTO>> getAll(){
        List<ClientDTO> clients = service.getAllClients();
        return ResponseEntity.ok(clients);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientDTO> getClient(@PathVariable(value = "id") String id){
        ClientDTO client = service.getClientById(id);
        if (client == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(client);
    }

    @CircuitBreaker(name = "paymentsCB", fallbackMethod = "fallBackGetClientCards")
    @GetMapping(value = "cards/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CardDTO>> getClientCards(@PathVariable(value = "id") String id){
        ClientDTO client = service.getClientById(id);
        if (client == null)
            return ResponseEntity.notFound().build();
        List<CardDTO> cards = service.getCards(id);
        return ResponseEntity.ok(cards);
    }

    @CircuitBreaker(name = "allCB", fallbackMethod = "fallBackGetAllDataFromClient")
    @GetMapping(value = "getAll/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getAllDataFromClient(@PathVariable(value = "id") String id){
        ClientDTO client = service.getClientById(id);
        if (client == null)
            return ResponseEntity.notFound().build();
        Map<String, Object> data = service.getClientCards(client);
        return ResponseEntity.ok(data);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientDTO> saveClient(@RequestBody Client client) throws ClientUnprocessableEntity {
        validator.validateClient(client);
        ClientDTO newClient = service.createClient(client);
        return ResponseEntity.ok(newClient);
    }

    @CircuitBreaker(name = "paymentsCB", fallbackMethod = "fallBackSaveClientCard")
    @PostMapping(value = "cards/{clientId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CardDTO> saveClientCard(@PathVariable(value = "clientId") String clientId,
                                                  @RequestBody Card card){

        CardDTO newCard = service.saveCard(clientId, card);
        return ResponseEntity.ok(newCard);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientDTO> updateClient(@PathVariable(value = "id") String id,
                                                  @RequestBody Client client) throws ClientUnprocessableEntity {
        validator.validateClient(client);
        ClientDTO updatedClient = service.updateClient(id, client);
        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteClient(@PathVariable(value = "id") String id){
        service.deleteClient(id);
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<List<CardDTO>> fallBackGetClientCards(@PathVariable(value = "id") String id, RuntimeException e){
        return new ResponseEntity("Can't get cards. Cards server under maintenance, sorry for the inconveniance.",
                HttpStatus.OK);
    }

    private ResponseEntity<Map<String, Object>> fallBackGetAllDataFromClient(@PathVariable(value = "id") String id, RuntimeException e){
        return new ResponseEntity("All servers under maintenance, sorry for the inconveniance.", HttpStatus.OK);
    }

    private ResponseEntity<CardDTO> fallBackSaveClientCard(@PathVariable(value = "clientId") String clientId,
                                                           @RequestBody Card card,
                                                           RuntimeException e){
        return new ResponseEntity("Can't save cards. Cards server under maintenance, sorry for the inconveniance.",
                HttpStatus.OK);
    }
}
