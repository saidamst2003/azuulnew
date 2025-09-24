package com.example.demo.controller;

import com.example.demo.dto.AtelierDTO;
import com.example.demo.model.Atelier;
import com.example.demo.service.AtelierService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PutMapping("/{id}")
    public AtelierDTO updateAtelier (@PathVariable Long id, @RequestBody AtelierDTO atelierDTO){
        return atelierService.updateAtelier(id,atelierDTO);
    }

 //get all

   @GetMapping
    public List<Atelier> getAllAtelier (){
        return atelierService.getAllAtelier();
   }
    @GetMapping("/{id}")
    public Optional<Atelier> getById (@PathVariable Long id){
         return atelierService.getAtelierById(id);
    }

}
