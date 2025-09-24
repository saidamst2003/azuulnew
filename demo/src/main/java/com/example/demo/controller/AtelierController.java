package com.example.demo.controller;

import com.example.demo.dto.AtelierDTO;
import com.example.demo.service.AtelierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ateliers")

public class AtelierController {
    private final AtelierService atelierService;

    public AtelierController(AtelierService atelierService){
        this.atelierService =atelierService;
    }

 @PostMapping
 public AtelierDTO createAtelier(@RequestBody AtelierDTO atelierDTO){
        return atelierService.createAtelier(atelierDTO);

 }
}
