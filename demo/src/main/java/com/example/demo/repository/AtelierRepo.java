package com.example.demo.repository;

import com.example.demo.dto.AtelierDTO;
import com.example.demo.model.Atelier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtelierRepo extends JpaRepository<Atelier,Long> {
    List<Atelier> id(Long id);

    AtelierDTO findAllById(Long id);
}
