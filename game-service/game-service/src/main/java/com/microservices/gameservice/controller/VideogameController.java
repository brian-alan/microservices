package com.microservices.gameservice.controller;

import com.microservices.gameservice.entity.Videogame;
import com.microservices.gameservice.service.VideogameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videogames")
public class VideogameController {

    @Autowired
    private VideogameService service;

    @GetMapping
    public ResponseEntity<List<Videogame>> getAllVideogames(){
        List<Videogame> games = service.getAllVideogames();
        return ResponseEntity.ok(games);
    }

    @PostMapping
    public ResponseEntity<Videogame> addNewVideogame(@RequestBody Videogame videogame){
        Videogame game = service.saveVideogame(videogame);
        return ResponseEntity.ok(game);
    }

}
