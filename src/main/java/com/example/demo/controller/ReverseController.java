package com.example.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ReverseData;

@CrossOrigin(origins={"http://localhost:5257"})
@RestController
@RequestMapping("/reverse")

public class ReverseController {

    @GetMapping("/{palavra}")
    public ReverseData reverse(@PathVariable String palavra) {
        String reverso = new StringBuilder(palavra).reverse().toString();
        
        if(reverso.equals(palavra)){
            return new ReverseData(reverso, true);
        }
        return new ReverseData(reverso, false);
    }
}
