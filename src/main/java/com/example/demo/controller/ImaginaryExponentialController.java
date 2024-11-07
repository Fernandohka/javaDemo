package com.example.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ImaExpData;

@CrossOrigin(origins={"http://localhost:5257"})
@RestController
@RequestMapping("/imaexp")

public class ImaginaryExponentialController {

    @GetMapping
    public ImaExpData ImaginaryExponential(Integer A, Integer b) {
        Double Re = A * Math.sin(b);
        Double Im = A * Math.cos(b);

        return new ImaExpData(Re, Im);
    }
}
