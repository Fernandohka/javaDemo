package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.City;
import com.example.demo.repositories.CityRepository;

@RestController
@RequestMapping("/cities")
public class CidadesController {

    @Autowired
    CityRepository repo;

    @GetMapping("/{query}")
    public ResponseEntity<List<City>> query(@PathVariable String query) {
        var cities = repo.findByName(query);
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<City>> tudo() {
        var cities = repo.findAll();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }
}
