package com.example.demo.dto;

import com.example.demo.model.Role;
import com.example.demo.validation.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterDTO(
        @NotBlank(message = "full name is required")
        String fullName,

        @NotBlank(message = "email is required")
        @Email(message = "please enter a valid email")
        @UniqueEmail
        String email,

        @NotBlank(message = "password is required")
        @Size(min = 6, message = "password must be at least 6 characters long")
        String password,

        Role role
) {}