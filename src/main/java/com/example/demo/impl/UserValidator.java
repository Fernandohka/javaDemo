package com.example.demo.impl;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;

public class UserValidator implements UserService {
    private static final String regex = "^(.+)@(.+).(.+)$";
    
    @Autowired
    UserRepository repo;

    public UserValidator(UserRepository repo){
        this.repo = repo;
    }

    @Override
    public boolean ValidarCamposVazios(String username, String senha, String email) {
        return !(username == null || senha == null || email == null || username.isEmpty() || senha.isEmpty() || email.isEmpty());
    }
    
    @Override
    public boolean ValidarSenha(String senha) {
        if(senha.length() < 8)
            return false;

        if(!senha.chars().anyMatch(c -> c >= 'a' && c <= 'z'))
            return false;
        
        if(!senha.chars().anyMatch(c -> c >= 'A' && c <= 'Z'))
            return false;

        return senha.chars().anyMatch(c -> c >= '0' && c <= '9');
    }

    @Override
    public boolean ValidarEmail(String email) {
        if(email.length() < 4)
            return false;

        return Pattern.matches(regex, email);
    }

    @Override
    public boolean ValidarUsername(String username) {
        return username.length() >= 4;
    }

    @Override
    public boolean ValidarUsuarioEmailEmUso(String username, String email) {
        System.out.println(repo.findByEmail(email));
        if(!repo.findByUsername(username).isEmpty())
            return false;

        return repo.findByEmail(email).isEmpty();
    }
    
}
