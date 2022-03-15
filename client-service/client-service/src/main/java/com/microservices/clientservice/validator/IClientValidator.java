package com.microservices.clientservice.validator;

import com.microservices.clientservice.entity.Client;
import com.microservices.clientservice.utils.exceptions.ClientUnprocessableEntity;
import org.springframework.stereotype.Service;

@Service
public interface IClientValidator {

    void validateClient(Client client) throws ClientUnprocessableEntity;
}
