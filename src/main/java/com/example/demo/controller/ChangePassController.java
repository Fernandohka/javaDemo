package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ChangePasswordData;
import com.example.demo.impl.ChangePassValidator;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;

@RestController
@RequestMapping("/changepassword")
public class ChangePassController {

    @Autowired
    UserRepository user;

    @PatchMapping
    public String ChangePass(@RequestBody ChangePasswordData novaSenha){
        ChangePassValidator validator = new ChangePassValidator(novaSenha.username(), user);
        
        if(!validator.ValidarUsuario(novaSenha.username()))
            return "Usuario nao cadastrado";

        if(!validator.ValidarSenha(novaSenha.password()))
            return "Senha incorreta";

        if(!validator.ValidarNovaSenha(novaSenha.newPassword()))
            return "Nova senha precisa possuir ao menos 8 caracteres; Ter letras maiusculas; Ter letras minusculas; Ter números.";

        if(!validator.ValidarRepeticaoSenha(novaSenha.newPassword(), novaSenha.repeatPassword()))
            return "A repetição de senha não está igual";
        
        User newUser = user.findByUsername(novaSenha.username()).getFirst();
        newUser.setPassword(novaSenha.newPassword());
        user.save(newUser);
        return "usuario updateado com sucesso";
    }
}
