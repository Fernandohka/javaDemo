package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SumResult;

@RestController
@RequestMapping("/test")
public class TestController {
    
    @GetMapping("/{value}")
    public SumResult test(@PathVariable Integer value, Integer value2) {
        if(value2 == null){
            value2 = 2;
        }
        var result = value + value2;
        var isEven = result % 2 == 0;
        return new SumResult(result, isEven);
    }
}
