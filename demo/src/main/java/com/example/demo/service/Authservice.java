package com.example.demo.service;

import com.example.demo.dto.RegisterDTO;
import com.example.demo.model.*;
import com.example.demo.repository.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Authservice {

    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;

    public Authservice(UserRepo userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

}
