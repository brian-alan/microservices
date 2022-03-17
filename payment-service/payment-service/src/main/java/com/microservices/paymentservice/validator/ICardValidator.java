package com.microservices.paymentservice.validator;

import com.microservices.paymentservice.entity.Card;
import com.microservices.paymentservice.utils.exceptions.CardUnprocessableEntity;

public interface ICardValidator {
    void cardValidator(Card card) throws CardUnprocessableEntity;
}
