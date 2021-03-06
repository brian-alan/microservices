package com.microservices.clientservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    private String cardNumber;
    private String expiryDate;
    private String cvv;
    private String clientId;

}
