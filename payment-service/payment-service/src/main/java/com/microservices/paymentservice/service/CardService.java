package com.microservices.paymentservice.service;

import com.microservices.paymentservice.dto.CardDTO;
import com.microservices.paymentservice.entity.Card;
import com.microservices.paymentservice.repository.CardRepository;
import com.microservices.paymentservice.utils.helpers.MHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardService {

    @Autowired
    private CardRepository repository;

    public List<CardDTO> getAllCards(){
        List <Card> cards = repository.findAll();
        return cards.stream().map(this::parseToCardDTO).collect(Collectors.toList());
    }

    public List<CardDTO> getCardsFromClient(String clientId){
        List<Card> cards = repository.findCardsByClientId(clientId);
        return cards.stream().map(this::parseToCardDTO).collect(Collectors.toList());
    }

    public CardDTO saveCard(Card card){
        Card savedCard = repository.insert(card);
        CardDTO cardDTO = parseToCardDTO(card);
        return cardDTO;
    }

    private CardDTO parseToCardDTO(Card card) {
        return MHelper.modelMapper().map(card, CardDTO.class);
    }
}
