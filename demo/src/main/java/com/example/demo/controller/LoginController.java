package com.example.demo.controller;

import com.example.demo.dto.LoginDTO;
import com.example.demo.model.Utilisateur;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/login")
@CrossOrigin
public class LoginController {

    private final UserService userService;

    public LoginController(
            final UserService userService
    ) {
        this.userService = userService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginDTO loginDTO) {

        Utilisateur utilisateur = userService.findByEmail(loginDTO.email());
        if (utilisateur == null) {
            return ResponseEntity.badRequest().body("Email ou mot de passe incorrect");
        }
        utilisateur.setPassword(loginDTO.password());
        return userService.verify(utilisateur);
    }
}
