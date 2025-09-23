package com.example.demo.mapper;


import com.example.demo.dto.AtelierDTO;
import com.example.demo.model.Atelier;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AtelierMapper {

AtelierDTO toDTO(Atelier atelier);


 Atelier toEntity(AtelierDTO atelierDTO);
}
