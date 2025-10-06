package com.example.demo.dto;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public record AtelierDTO (
    String nom,
    String description,
    Date date,
    LocalTime heure,
    String categorie
){

}
