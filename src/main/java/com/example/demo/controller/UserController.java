package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.impl.BcryptPasswordEncoderService;
import com.example.demo.impl.UserValidator;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

    // @Autowired
    // LoginService service;

    @Autowired
    UserRepository user;

    // @PostMapping
    // public ResponseEntity<String> login(@RequestBody LoginData data){
    //     var id = service.login(data.login(), data.senha());

    //     if(id == 1){
    //         return ResponseEntity.ok("Bem vindo donzinho");
    //     }
    //     return ResponseEntity.status(404).build();
    // }

    @PostMapping
    public ResponseEntity<String> registrar(@RequestBody User newUser) {
        UserValidator validator = new UserValidator(user);
        if(!validator.ValidarCamposVazios(newUser.getUsername(), newUser.getPassword(), newUser.getEmail()))
            return new ResponseEntity<>("Preencha todos os campos", HttpStatus.BAD_REQUEST);
        
        if(!validator.ValidarEmail(newUser.getEmail()))
            return new ResponseEntity<>("Email com menos de 4 caracteres ou fora do formato padrão", HttpStatus.BAD_REQUEST);

        if(!validator.ValidarUsername(newUser.getUsername()))
            return new ResponseEntity<>("Username com menos de 4 caracteres", HttpStatus.BAD_REQUEST);

        if(!validator.ValidarUsuarioEmailEmUso(newUser.getUsername(), newUser.getEmail()))
            return new ResponseEntity<>("Usuario ou email em uso", HttpStatus.BAD_REQUEST);
            
        BcryptPasswordEncoderService encoder = new BcryptPasswordEncoderService();
        newUser.setPassword(encoder.encode(newUser.getPassword()));
        user.save(newUser);
        return new ResponseEntity<>("Usuario cadastrado com sucesso", HttpStatus.OK);
    }
}
