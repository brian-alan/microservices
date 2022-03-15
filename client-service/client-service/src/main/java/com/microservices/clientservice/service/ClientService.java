package com.microservices.clientservice.service;

import com.microservices.clientservice.dto.CardDTO;
import com.microservices.clientservice.dto.ClientDTO;
import com.microservices.clientservice.entity.Client;
import com.microservices.clientservice.feignclients.CardFeignClient;
import com.microservices.clientservice.model.Card;
import com.microservices.clientservice.repository.ClientRepository;
import com.microservices.clientservice.utils.helpers.MHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.clientservice.utils.hash.BCrypt;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CardFeignClient feignClient;

    public List<ClientDTO> getAllClients(){
        List<Client> clients = repository.findAll();
        return clients.stream().map(this::parseToClientDTO).collect(Collectors.toList());
    }

    public ClientDTO getClientById(String id){
        ClientDTO clientDTO = null;
        Client client = repository.findClientById(id).orElse(null);
        if (client!=null)
            clientDTO = parseToClientDTO(client);
        return clientDTO;
    }

    public ClientDTO createClient(Client client){
        client.setPassword(BCrypt.hashpw(client.getPassword(), BCrypt.gensalt()));
        Client client1 = repository.save(client);
        return parseToClientDTO(client1);
    }

    public ClientDTO updateClient(String id, Client client){
        ClientDTO clientDTO = null;
        Client updatedClient = repository.findClientById(id).orElse(null);
        if(client != null){
            updatedClient.setFirstName(client.getFirstName());
            updatedClient.setLastName(client.getLastName());
            updatedClient.setEmail(client.getEmail());
            updatedClient.setPassword(BCrypt.hashpw(client.getPassword(), BCrypt.gensalt()));
            updatedClient.setAge(client.getAge());
            updatedClient.setGender(client.getGender());
            clientDTO = parseToClientDTO(updatedClient);
        }
        return clientDTO;
    }

    public void deleteClient(String id){
        Client client = repository.findClientById(id).orElse(null);
        if (client != null)
            repository.delete(client);
    }

    public List<CardDTO> getCards(String id){
        List<CardDTO> cards = feignClient.getCardsFromClient(id);
        return cards;
    }

    public CardDTO saveCard(String clientId, Card card){
        card.setClientId(clientId);
        CardDTO newCard = feignClient.saveCard(card);
        return newCard;
    }

    public Map<String, Object> getClientCards(ClientDTO client){
        Map<String, Object> result = new HashMap<>();
        result.put("Client", client);
        List<CardDTO> cards = feignClient.getCardsFromClient(client.getId());
        result.put("Cards", cards);

        return result;
    }

    private ClientDTO parseToClientDTO(Client client) {
        return MHelpers.modelMapper().map(client, ClientDTO.class);
    }



}
