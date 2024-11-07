package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Curitibadata;
import com.example.demo.impl.cepValidator;
import com.example.demo.impl.cpfValidator;

@CrossOrigin(origins={"http://localhost:5257"})
@RestController
@RequestMapping("/curitiba")
public class CuritibaRegisterController {

    
    @GetMapping
    public ResponseEntity<Curitibadata> curitiba(String cep, String cpf) {
        ArrayList<String> mensagem = new ArrayList<>();
        if (cep == null || cep.isEmpty())
            mensagem.add("Campo de cep vazio");
        else{
            cepValidator cepValidator = new cepValidator();
            if(!cepValidator.validarCEP(cep))
                mensagem.add("cep Invalido");
        }
        
        if(cpf == null || cpf.isEmpty())
            mensagem.add("Campo de cpf vazio");
        else{
            cpfValidator cpfValidator = new cpfValidator();
            if(!cpfValidator.validarCPF(cpf))
                mensagem.add("cpf Invalido");
        }
            
        if(!mensagem.isEmpty())
            return new ResponseEntity<>(new Curitibadata(mensagem.toString(), false), HttpStatus.BAD_REQUEST);
        else
            mensagem.add("Tudo Certo");

        return new ResponseEntity<>(new Curitibadata(mensagem.toString(), true), HttpStatus.OK);
    }
}
