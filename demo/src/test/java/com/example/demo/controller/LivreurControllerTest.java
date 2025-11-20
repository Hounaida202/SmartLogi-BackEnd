package com.example.demo.controller;

import com.example.demo.dto.LivreurDTO;
import com.example.demo.service.LivreurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LivreurControllerTest {

    @Mock
    private LivreurService livreurService;

    @InjectMocks
    private LivreurController livreurController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // ----------------------------------------------------

    @Test
    void testCreateLivreur() {
        LivreurDTO dto = new LivreurDTO();
        LivreurDTO savedDto = new LivreurDTO();

        when(livreurService.saveLivreur(dto)).thenReturn(savedDto);

        ResponseEntity<LivreurDTO> response = livreurController.createLivreur(dto);

        assertEquals(savedDto, response.getBody());
        verify(livreurService).saveLivreur(dto);
    }
}
