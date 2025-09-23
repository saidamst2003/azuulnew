package com.example.demo.controller;

import com.example.demo.service.AtelierService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AtelierController {
    private final AtelierService atelierService;

    public AtelierController(AtelierService atelierService){
        this.atelierService =atelierService;
    }

}
