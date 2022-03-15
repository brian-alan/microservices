package com.microservices.clientservice.validator;

import com.microservices.clientservice.entity.Client;
import com.microservices.clientservice.utils.exceptions.ClientUnprocessableEntity;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ClientValidator implements IClientValidator{
    @Override
    public void validateClient(Client client) throws ClientUnprocessableEntity {
        if (client.getFirstName() == null || client.getFirstName().isEmpty()){
            message("First name is obligatory.");
        }
        if (client.getFirstName().length() < 2){
            message("First name length must be 3 characters long.");
        }
        String firstNamePattern = "^[A-Z]{1}[a-z]{2,20}";
        Pattern fnPattern = Pattern.compile(firstNamePattern);
        Matcher fnMatcher = fnPattern.matcher(client.getFirstName());
        if (!fnMatcher.matches()){
            message("First name must start with capital letter and only contain characters.");
        }
        if (client.getLastName() == null || client.getLastName().isEmpty()){
            message("Second name is obligatory.");
        }
        if (client.getLastName().length() < 2){
            message("Last name length must be 3 characters long.");
        }
        String lastNamePattern = "^[A-Z]{1}[a-z]{2,20}";
        Pattern lnPattern = Pattern.compile(lastNamePattern);
        Matcher lnMatcher = lnPattern.matcher(client.getLastName());
        if (!lnMatcher.matches()){
            message("Last name must start with capital letter and only contain characters.");
        }
        if (client.getEmail() == null || client.getEmail().isEmpty()){
            message("Email is obligatory.");
        }
        /*
        String emailPattern = "^(.+)@(.+)$";
        Pattern ePattern = Pattern.compile(emailPattern);
        Matcher eMatcher = ePattern.matcher(client.getEmail());
        if (!eMatcher.matches()){
            message("Invalid email.");
        }
        */
        if (client.getPassword() == null || client.getPassword().isEmpty()){
            message("Password is obligatory");
        }
        if (client.getPassword().length()<5){
            message("Password must be over 5 characters");
        }
        if (client.getAge() < 18){
            message("Invalid age.");
        }
        /*
        if (client.getGender() != 'M' || client.getGender() != 'F'){
            message("Invalid gender.");
        }
        */
    }

    private void message(String message) throws ClientUnprocessableEntity {
        throw new ClientUnprocessableEntity(message);
    }
}
