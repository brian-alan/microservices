package com.microservices.clientservice.controller;

import com.microservices.clientservice.dto.CardDTO;
import com.microservices.clientservice.dto.ClientDTO;
import com.microservices.clientservice.entity.Client;
import com.microservices.clientservice.model.Card;
import com.microservices.clientservice.utils.exceptions.ClientUnprocessableEntity;
import com.microservices.clientservice.service.ClientService;
import com.microservices.clientservice.validator.ClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(value = "cards/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CardDTO>> getClientCards(@PathVariable(value = "id") String id){
        ClientDTO client = service.getClientById(id);
        if (client == null)
            return ResponseEntity.notFound().build();
        List<CardDTO> cards = service.getCards(id);
        return ResponseEntity.ok(cards);
    }

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
}
