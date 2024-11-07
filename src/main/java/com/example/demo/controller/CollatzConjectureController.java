package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CollatzData;

@CrossOrigin(origins={"http://localhost:5257"})
@RestController
@RequestMapping("/collatz")

public class CollatzConjectureController {

    @GetMapping
    public ResponseEntity<CollatzData> Collatz(Integer current, Integer step) {
        for(int i = 0; i < step; i++)
            current = (current % 2 == 0) ? current / 2 : 3 * current + 1;

        if(step < 0 || current < 0)
            return ResponseEntity.badRequest().build();
        
        return new ResponseEntity<>(new CollatzData(current), HttpStatus.OK);
    }
}
