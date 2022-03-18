package com.microservices.videogameservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {

    @Id
    private String id;
    private String videogameId;
    private String paymentId;
    private String userId;
}
