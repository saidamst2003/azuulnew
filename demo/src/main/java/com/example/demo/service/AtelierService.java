package com.example.demo.service;

import com.example.demo.dto.AtelierDTO;
import com.example.demo.mapper.AtelierMapper;
import com.example.demo.model.Atelier;
import com.example.demo.repository.AtelierRepo;
import org.springframework.stereotype.Service;

@Service
public class AtelierService {

    private final AtelierRepo atelierRepo;
    private final AtelierMapper atelierMapper;

    public AtelierService(AtelierRepo atelierRepo, AtelierMapper atelierMapper){
        this.atelierRepo = atelierRepo;
        this.atelierMapper = atelierMapper;
    }

    //creation atelier

    public AtelierDTO createAtelier(Atelier atelier){
        Atelier saved =atelierRepo.save(atelier);
        return atelierMapper.toDTO(saved);
    }
}
