package com.example.demo.dto;

public record ChangePasswordData(
    String username,
    String password,
    String newPassword,
    String repeatPassword
) {
    
}
