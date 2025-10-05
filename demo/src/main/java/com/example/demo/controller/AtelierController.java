package com.example.demo.controller;

import com.example.demo.dto.AtelierDTO;
import com.example.demo.model.Atelier;
import com.example.demo.service.AtelierService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ateliers")
@CrossOrigin("http://localhost:4200")
public class AtelierController {
    private final AtelierService atelierService;

    public AtelierController(AtelierService atelierService){
        this.atelierService =atelierService;
    }

    //create
 @PostMapping
 @PreAuthorize("hasRole('ADMIN')")

 public AtelierDTO createAtelier(@RequestBody AtelierDTO atelierDTO){
        return atelierService.createAtelier(atelierDTO);

 }
 //update
 @PreAuthorize("hasRole('ADMIN')")
 @PutMapping("/{id}")
    public AtelierDTO updateAtelier (@PathVariable Long id, @RequestBody AtelierDTO atelierDTO){
        return atelierService.updateAtelier(id,atelierDTO);
    }

 //get all

   @GetMapping
    public List<Atelier> getAllAtelier (){
        return atelierService.getAllAtelier();
   }


   //get by id

    @GetMapping("/{id}")
    public Optional<Atelier> getById (@PathVariable Long id){
         return atelierService.getAtelierById(id);
    }


    //delete
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void  deletAtelier (@PathVariable Long id){
            atelierService.deletAtelier(id);
   }

}
