package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDTO (
    @NotBlank(message = "l'e-mail est requis")
    @Email(message = "veuillez entrer une adresse e-mail valide")
    String email,
    @NotBlank(message = "l'e-mail est requis")
    String password
            ){

    }

