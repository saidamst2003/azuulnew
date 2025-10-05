package com.example.demo.repository;

import com.example.demo.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Utilisateur, Long> {
    Utilisateur findUserByEmail(String email);
}