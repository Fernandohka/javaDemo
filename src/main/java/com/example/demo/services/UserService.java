package com.example.demo.services;

public interface UserService {
    boolean ValidarCamposVazios(String username, String senha, String email);
    boolean ValidarSenha(String senha);
    boolean ValidarEmail(String email);
    boolean ValidarUsername(String username);
    boolean ValidarUsuarioEmailEmUso(String username, String email);
}
