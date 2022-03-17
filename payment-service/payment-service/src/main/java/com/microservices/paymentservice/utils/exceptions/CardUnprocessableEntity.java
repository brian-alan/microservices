package com.microservices.paymentservice.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class CardUnprocessableEntity extends Exception{

    public CardUnprocessableEntity(String message){
        super(message);
    }
}
