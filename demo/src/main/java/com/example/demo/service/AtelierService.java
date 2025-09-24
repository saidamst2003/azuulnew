package com.example.demo.service;

import com.example.demo.dto.AtelierDTO;
import com.example.demo.mapper.AtelierMapper;
import com.example.demo.model.Atelier;
import com.example.demo.repository.AtelierRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    //update atelier

    public  AtelierDTO updateAtelier(Long id,AtelierDTO atelierDTO){

        Atelier atelier = atelierRepo.findById(id).orElseThrow(() -> new RuntimeException("atelier not found "));

        atelier.setNom(atelierDTO.nom());
        atelier.setDescription(atelierDTO.description());
        atelier.setCategorie(atelierDTO.categorie());
        atelier.setDate(atelierDTO.date());
        atelier.setHeure(atelierDTO.heure());

        Atelier update =atelierRepo.save(atelier);

        return atelierMapper.toDTO(update);
    }


    //get all
    public List<Atelier> getAllAtelier(){
        return atelierRepo.findAll();
    }

    // get by id
    public Optional<Atelier> getAtelierById(Long id){
        return atelierRepo.findById(id);

    }

}
