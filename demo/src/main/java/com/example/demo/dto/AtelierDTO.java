package com.example.demo.dto;


import java.time.LocalTime;
import java.util.Date;

public record AtelierDTO (
    Long id,
    String nom,
    String description,
    Date date,
    LocalTime heure,
    String categorie
){
}
