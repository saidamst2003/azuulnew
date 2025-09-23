package com.example.demo.repository;

import com.example.demo.model.Atelier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtelierRepo extends JpaRepository<Atelier,Long> {
}
