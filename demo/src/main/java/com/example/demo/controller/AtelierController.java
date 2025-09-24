package com.example.demo.controller;

import com.example.demo.dto.AtelierDTO;
import com.example.demo.service.AtelierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ateliers")

public class AtelierController {
    private final AtelierService atelierService;

    public AtelierController(AtelierService atelierService){
        this.atelierService =atelierService;
    }

    //create
 @PostMapping
 public AtelierDTO createAtelier(@RequestBody AtelierDTO atelierDTO){
        return atelierService.createAtelier(atelierDTO);

 }

 //update
    //
    @PutMapping("/{id}")
    public AtelierDTO updateAtelier (@PathVariable Long id, @RequestBody AtelierDTO atelierDTO){
        return atelierService.updateAtelier(id,atelierDTO);
    }

}
