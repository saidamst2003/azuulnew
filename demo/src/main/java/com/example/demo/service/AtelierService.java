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

    public AtelierDTO createAtelier(AtelierDTO atelier){
        Atelier atelierEntity = atelierMapper.toEntity(atelier);

        Atelier saved =atelierRepo.save(atelierEntity);
        return atelierMapper.toDTO(saved);
    }
}
