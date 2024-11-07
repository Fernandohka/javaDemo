package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.impl.UserValidator;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;

@RestController
@RequestMapping("/create")
public class CreateUserController {
    
    @Autowired
    UserRepository user;

    @PostMapping
    public String criarUser(@RequestBody User newUser){
        UserValidator validator = new UserValidator(user);
        if(!validator.ValidarCamposVazios(newUser.getUsername(), newUser.getPassword(), newUser.getEmail()))
            return "Preencha todos os campos";
        
        if(!validator.ValidarEmail(newUser.getEmail()))
            return "Email com menos de 4 caracteres ou fora do formato padrão";

        if(!validator.ValidarUsername(newUser.getUsername()))
            return "Username com menos de 4 caracteres";

        if(!validator.ValidarSenha(newUser.getPassword()))
            return "A senha precisa Possuir ao menos 8 caracteres; Ter letras maiusculas; Ter letras minusculas; Ter números.";

        if(!validator.ValidarUsuarioEmailEmUso(newUser.getUsername(), newUser.getEmail()))
            return "Usuario ou email em uso";
            
        user.save(newUser);
        return "Usuario cadastrado com sucesso";
    }
}
