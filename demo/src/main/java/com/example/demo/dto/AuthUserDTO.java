package com.example.demo.dto;

import com.example.demo.model.Role;

public record AuthUserDTO (
        Long id,
        String fullName,
        String email,
        Role role
) {
}