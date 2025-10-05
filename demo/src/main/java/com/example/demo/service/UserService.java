package com.example.demo.service;

import com.example.demo.dto.AuthUserDTO;
import com.example.demo.dto.RegisterDTO;
import com.example.demo.exception.PasswordIncorrectException;
import com.example.demo.model.*;
import com.example.demo.repository.UserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class UserService {

    private final UserRepo userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepo userRepository, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.encoder = new BCryptPasswordEncoder(12);
    }

    public Utilisateur registerUser(RegisterDTO registerDTO) {
        if (userRepository.findUserByEmail(registerDTO.email()) != null) {
            throw new IllegalArgumentException("User with this email already exists.");
        }

        String encryptedPassword = encoder.encode(registerDTO.password());
        Role role = registerDTO.role();
        Utilisateur newUser;

        switch (role) {
            case ADMIN -> newUser = new Admin(registerDTO.fullName(), registerDTO.email(), encryptedPassword, Role.ADMIN);
            case CLIENT -> newUser = new Client(registerDTO.fullName(), registerDTO.email(), encryptedPassword, Role.CLIENT);
            case COACH -> newUser = new Coach(registerDTO.fullName(), registerDTO.email(), encryptedPassword, Role.COACH, "specialite par defaut");
            default -> throw new IllegalArgumentException("Unknown role: " + role);
        }

        return userRepository.save(newUser);
    }

    public ResponseEntity<?> verify(Utilisateur user) {
        try {
            Utilisateur userFromDb = userRepository.findUserByEmail(user.getEmail());
            if (userFromDb == null || !encoder.matches(user.getPassword(), userFromDb.getPassword())) {
                throw new PasswordIncorrectException("Email ou mot de passe incorrect");
            }

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
            );

            if (authentication.isAuthenticated()) {
                AuthUserDTO authUser = getAuthenticatedUser(user.getEmail());
                String token = jwtService.generateToken(authUser);
                Map<String, String> response = new HashMap<>();
                response.put("token", token);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            throw new PasswordIncorrectException("Invalid credentials.");
        } catch (AuthenticationException e) {
            throw new PasswordIncorrectException("Invalid credentials.");
        }
    }

    public AuthUserDTO getAuthenticatedUser(String email) {
        Utilisateur user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new PasswordIncorrectException("User not found after authentication.");
        }
        return new AuthUserDTO(user.getId(), user.getFullName(), user.getEmail(), user.getRole());
    }

    public Utilisateur findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
