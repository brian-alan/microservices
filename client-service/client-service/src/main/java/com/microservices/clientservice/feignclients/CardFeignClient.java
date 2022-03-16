package com.microservices.clientservice.feignclients;

import com.microservices.clientservice.dto.CardDTO;
import com.microservices.clientservice.model.Card;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "payment-service", path = "/cards")
public interface CardFeignClient {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<CardDTO> getAll();

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CardDTO> getCardsFromClient(@PathVariable(value = "userId") String userId);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    CardDTO saveCard(@RequestBody Card card);
}
