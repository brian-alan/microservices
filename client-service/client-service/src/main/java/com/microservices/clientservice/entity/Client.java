package com.microservices.clientservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int age;
    private char gender;

}
