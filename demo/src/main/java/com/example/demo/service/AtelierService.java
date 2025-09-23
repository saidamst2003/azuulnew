package com.example.demo.service;

import com.example.demo.model.Atelier;
import com.example.demo.repository.AtelierRepo;
import org.springframework.stereotype.Service;

@Service
public class AtelierService {

    private final AtelierRepo atelierRepo;

    public AtelierService(AtelierRepo atelierRepo){
        this.atelierRepo = atelierRepo;
    }

}
