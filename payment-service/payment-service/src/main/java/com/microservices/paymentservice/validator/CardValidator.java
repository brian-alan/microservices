package com.microservices.paymentservice.validator;

import com.microservices.paymentservice.entity.Card;
import com.microservices.paymentservice.utils.exceptions.CardUnprocessableEntity;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CardValidator implements ICardValidator{

    @Override
    public void cardValidator(Card card) throws CardUnprocessableEntity {
        if(card.getCardNumber() == null || card.getCardNumber().isEmpty()){
            message("Card number is obligatory.");
        }
        String numberCardPattern = "^[0-9]{16}";
        Pattern nCPattern = Pattern.compile(numberCardPattern);
        Matcher nCMatcher = nCPattern.matcher(card.getCardNumber());
        if (!nCMatcher.matches()) {
            message("Card number must contain only 16 digits.");
        }
        if (card.getCvv() < 100 || card.getCvv() > 999){
            message("CVV must contain only 3 digits.");
        }

    }

    private void message(String message) throws CardUnprocessableEntity {
        throw new CardUnprocessableEntity(message);
    }
}
