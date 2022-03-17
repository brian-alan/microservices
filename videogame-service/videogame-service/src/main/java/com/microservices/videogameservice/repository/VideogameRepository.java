package com.microservices.videogameservice.repository;

import com.microservices.videogameservice.entity.Videogame;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideogameRepository extends MongoRepository<Videogame, String> {
}
