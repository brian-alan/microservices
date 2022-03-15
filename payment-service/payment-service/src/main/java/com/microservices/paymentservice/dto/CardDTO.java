package com.microservices.paymentservice.dto;

import lombok.Data;

@Data
public class CardDTO {

    private String cardNumber;
    private String expiryDate;
    private String clientId;

}
