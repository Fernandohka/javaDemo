package com.example.demo.impl;

import com.example.demo.services.cepService;

public class cepValidator implements cepService{

    @Override
    public Boolean validarCEP(String cep) {
        return !(cep.length() != 8 || cep.chars().anyMatch(c -> !(c >= '0' && c <= '9')));
    }
    
}
