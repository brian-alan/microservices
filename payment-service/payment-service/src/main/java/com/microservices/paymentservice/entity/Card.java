package com.microservices.paymentservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Id
    private String id;
    private String cardNumber;
    private String expiryDate;
    private int cvv;
    private String clientId;

}
