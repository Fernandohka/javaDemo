package com.example.demo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.ChangePassService;

public class ChangePassValidator implements ChangePassService {

    @Autowired
    UserRepository repo;

    List<User> user;

    public ChangePassValidator(String userName, UserRepository repo) {
        this.repo = repo;
        user = this.repo.findByUsername(userName);
    }

    @Override
    public boolean ValidarSenha(String senha) {
        return user.get(0).getPassword().equals(senha);
    }
    
    @Override
    public boolean ValidarNovaSenha(String newSenha) {
        var validator = new UserValidator(repo);
        return validator.ValidarSenha(newSenha);
    }
    
    @Override
    public boolean ValidarRepeticaoSenha(String senha, String newSenha) {
        return senha.equals(newSenha);
    }
    
    @Override
    public boolean ValidarUsuario(String username) {
        return user.get(0).getUsername().equals(username);
    }
}
