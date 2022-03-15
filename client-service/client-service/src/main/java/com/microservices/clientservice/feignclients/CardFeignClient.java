package com.microservices.clientservice.feignclients;

import com.microservices.clientservice.dto.CardDTO;
import com.microservices.clientservice.model.Card;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "payment-service", url = "http://localhost:9081/cards")
public interface CardFeignClient {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<CardDTO> getAll();

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CardDTO> getCardsFromClient(@PathVariable(value = "userId") String userId);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    CardDTO saveCard(@RequestBody Card card);
}
