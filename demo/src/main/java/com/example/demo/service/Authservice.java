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

//creer registration avec role
    public Utilisateur registerUser(RegisterDTO registerDTO, String role) {
        Utilisateur newUser;

        if (role.equalsIgnoreCase("admin")) {
            newUser = new Admin(
                    registerDTO.fullName(),
                    registerDTO.email(),
                    passwordEncoder.encode(registerDTO.password()),
                    Role.ADMIN
            );
        } else if (role.equalsIgnoreCase("client")) {
            newUser = new Client(
                    registerDTO.fullName(),
                    registerDTO.email(),
                    passwordEncoder.encode(registerDTO.password()),
                    Role.CLIENT
            );
        } else if (role.equalsIgnoreCase("coach")) {
            // Ici pas de spécialité fournie → valeur fixe par défaut
            newUser = new Coach(
                    registerDTO.fullName(),
                    registerDTO.email(),
                    passwordEncoder.encode(registerDTO.password()),
                    Role.COACH,
                    "Aucune spécialité"
            );
        } else {
            throw new IllegalArgumentException("Rôle non reconnu : " + role);
        }

        return userRepository.save(newUser);
    }

}
