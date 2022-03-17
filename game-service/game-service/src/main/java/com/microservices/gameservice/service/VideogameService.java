package com.microservices.gameservice.service;

import com.microservices.gameservice.entity.Videogame;
import com.microservices.gameservice.repository.VideogameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideogameService {

    @Autowired
    private VideogameRepository repository;

    public List<Videogame> getAllVideogames(){
        return repository.findAll();
    }

    public Videogame saveVideogame(Videogame videogame){
        return repository.save(videogame);
    }
}
