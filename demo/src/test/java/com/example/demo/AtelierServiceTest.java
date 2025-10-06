package com.example.demo;

import com.example.demo.dto.AtelierDTO;
import com.example.demo.mapper.AtelierMapper;
import com.example.demo.model.Atelier;
import com.example.demo.repository.AtelierRepo;
import com.example.demo.service.AtelierService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AtelierServiceTest {

    @Mock
    private AtelierRepo atelierRepo;

    @Mock
    private AtelierMapper atelierMapper;

    @InjectMocks
    private AtelierService atelierService;

    private AtelierDTO atelierDTO;
    private Atelier atelier;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        atelierDTO = new AtelierDTO(
                "Nom Atelier",
                "Description Atelier",
                new Date(),
                LocalTime.of(10, 0),
                "Catégorie1"
        );

        atelier = new Atelier();
        atelier.setNom(atelierDTO.nom());
        atelier.setDescription(atelierDTO.description());
        atelier.setDate(atelierDTO.date());
        atelier.setHeure(atelierDTO.heure());
        atelier.setCategorie(atelierDTO.categorie());
    }

    @Test
    void testCreateAtelier() {
        when(atelierMapper.toEntity(atelierDTO)).thenReturn(atelier);
        when(atelierRepo.save(atelier)).thenReturn(atelier);
        when(atelierMapper.toDTO(atelier)).thenReturn(atelierDTO);

        AtelierDTO result = atelierService.createAtelier(atelierDTO);

        assertNotNull(result);
        assertEquals(atelierDTO.nom(), result.nom());
        verify(atelierRepo, times(1)).save(atelier);
    }

    @Test
    void testUpdateAtelier() {
        Long id = 1L;

        when(atelierRepo.findById(id)).thenReturn(Optional.of(atelier));
        when(atelierRepo.save(atelier)).thenReturn(atelier);
        when(atelierMapper.toDTO(atelier)).thenReturn(atelierDTO);

        AtelierDTO result = atelierService.updateAtelier(id, atelierDTO);

        assertEquals(atelierDTO.nom(), result.nom());
        verify(atelierRepo, times(1)).save(atelier);
    }

    @Test
    void testGetAllAtelier() {
        when(atelierRepo.findAll()).thenReturn(Arrays.asList(atelier));

        List<Atelier> ateliers = atelierService.getAllAtelier();

        assertEquals(1, ateliers.size());
        verify(atelierRepo, times(1)).findAll();
    }

}
