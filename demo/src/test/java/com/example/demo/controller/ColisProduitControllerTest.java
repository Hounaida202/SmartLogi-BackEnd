package com.example.demo.controller;

import com.example.demo.dto.ColisProduitDTO;
import com.example.demo.entity.ColisProduit;
import com.example.demo.service.ColisProduitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ColisProduitControllerTest {

    @Mock
    private ColisProduitService colisProduitService;

    @InjectMocks
    private ColisProduitController colisProduitController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // ----------------------------------------------------

    @Test
    void testAssigner() {
        ColisProduitDTO dto = new ColisProduitDTO();
        ColisProduit colisProduit = new ColisProduit();

        when(colisProduitService.assignerProduitAuColis(dto)).thenReturn(colisProduit);

        ColisProduit result = colisProduitController.assigner(dto);

        assertEquals(colisProduit, result);
        verify(colisProduitService).assignerProduitAuColis(dto);
    }
}
