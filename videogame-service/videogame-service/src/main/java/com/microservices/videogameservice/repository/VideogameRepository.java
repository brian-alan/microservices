package com.microservices.videogameservice.repository;

import com.microservices.videogameservice.entity.Videogame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideogameRepository extends JpaRepository<Videogame, Integer> {
}
