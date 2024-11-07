package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginData;
import com.example.demo.dto.tokenData;

@RestController
@RequestMapping("/login")
public class LoginController {
    
    @PostMapping
    public tokenData login(@RequestBody LoginData userData) {

        return new tokenData("📝", null);
    }
}
