package com.microservices.clientservice.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ClientUnprocessableEntity extends Exception{

    public ClientUnprocessableEntity(String message){
        super(message);
    }
}
