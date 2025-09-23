package com.example.demo.service;

import com.example.demo.dto.AuthUserDTO;
import org.springframework.security.core.userdetails.User;

public interface UserService {
    String login(User user);
    User findByEmail(String email);
    AuthUserDTO getAuthenticatedUser(String email);
}
