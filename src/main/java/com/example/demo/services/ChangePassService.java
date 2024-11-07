package com.example.demo.services;

public interface ChangePassService {
    boolean ValidarSenha(String senha);
    boolean ValidarNovaSenha(String newSenha);
    boolean ValidarRepeticaoSenha(String senha, String newSenha);
    boolean ValidarUsuario(String username);
}
