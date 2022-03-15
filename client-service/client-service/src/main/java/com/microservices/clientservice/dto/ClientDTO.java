package com.microservices.clientservice.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ClientDTO implements Serializable {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private char gender;


}
