package com.microservices.gameservice.repository;

import com.microservices.gameservice.entity.Videogame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideogameRepository extends JpaRepository<Videogame, Integer> {
}
