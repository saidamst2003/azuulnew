package com.example.demo.service;

import com.example.demo.model.Utilisateur;
import com.example.demo.model.UserPrincipal;
import com.example.demo.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo utilisateurRepository;

    public CustomUserDetailsService(UserRepo utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findUserByEmail(email);
        return new UserPrincipal(utilisateur);
    }
}
