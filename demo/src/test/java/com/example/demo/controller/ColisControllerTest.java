package com.example.demo.controller;

import com.example.demo.dto.ColisAvecZoneDTO;
import com.example.demo.dto.ColisDTO;
import com.example.demo.dto.UpdateStautColisDTO;
import com.example.demo.service.ColisService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ColisControllerTest {

    @Mock
    private ColisService colisService;

    @InjectMocks
    private ColisController colisController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // ----------------------------------------------------

    @Test
    void testCreateColis() {
        ColisDTO dto = new ColisDTO();
        ColisDTO saved = new ColisDTO();

        when(colisService.saveColis(dto)).thenReturn(saved);

        ResponseEntity<ColisDTO> response = colisController.createColis(dto);

        assertEquals(saved, response.getBody());
        verify(colisService).saveColis(dto);
    }

    // ----------------------------------------------------

    @Test
    void testHeyhey() {
        ColisAvecZoneDTO dto = new ColisAvecZoneDTO();

        when(colisService.creerColisAvecZone(dto)).thenReturn(dto);

        ResponseEntity<?> response = colisController.heyhey(dto);

        assertEquals("created with success", response.getBody());
        verify(colisService).creerColisAvecZone(dto);
    }

    // ----------------------------------------------------

    @Test
    void testGetColisByClient() {
        PageRequest pageable = PageRequest.of(0, 10);
        Page<ColisDTO> page = new PageImpl<>(Collections.emptyList());

        when(colisService.getColisByClientAndStatut(1L, pageable)).thenReturn(page);

        ResponseEntity<Page<ColisDTO>> response = colisController.getColisByClient(1L, pageable);

        assertEquals(page, response.getBody());
        verify(colisService).getColisByClientAndStatut(1L, pageable);
    }

    // ----------------------------------------------------

    @Test
    void testGetStatutParDestinataire() {
        List<String> statuts = Arrays.asList("Créé", "Livré");

        when(colisService.getStatutColisParDestinataire(2L)).thenReturn(statuts);

        List<String> result = colisController.getStatutParDestinataire(2L);

        assertEquals(statuts, result);
        verify(colisService).getStatutColisParDestinataire(2L);
    }

    // ----------------------------------------------------

    @Test
    void testGetColisParLivreur() {
        List<ColisAvecZoneDTO> list = Collections.singletonList(new ColisAvecZoneDTO());

        when(colisService.getColisAvecZoneParLivreur(3L)).thenReturn(list);

        List<ColisAvecZoneDTO> result = colisController.getColisParLivreur(3L);

        assertEquals(list, result);
        verify(colisService).getColisAvecZoneParLivreur(3L);
    }

    // ----------------------------------------------------

    @Test
    void testUpdateStatut() {
        UpdateStautColisDTO dto = new UpdateStautColisDTO();

        when(colisService.updateStautColis(4L, dto)).thenReturn(dto);

        ResponseEntity<?> result = colisController.updateStatut(4L, dto);

        assertEquals("updated with success", result.getBody());
        verify(colisService).updateStautColis(4L, dto);
    }

    // ----------------------------------------------------

    @Test
    void testGetColisSansLivreur() {
        List<ColisAvecZoneDTO> list = Collections.singletonList(new ColisAvecZoneDTO());

        when(colisService.getColisSansLivreur()).thenReturn(list);

        List<ColisAvecZoneDTO> result = colisController.getColisSansLivreur();

        assertEquals(list, result);
        verify(colisService).getColisSansLivreur();
    }

    // ----------------------------------------------------

    @Test
    void testAssignerLivreur() {
        ColisAvecZoneDTO dto = new ColisAvecZoneDTO();

        when(colisService.assignerLivreurAuColis(5L, 9L)).thenReturn(dto);

        ColisAvecZoneDTO result = colisController.assignerLivreur(5L, 9L);

        assertEquals(dto, result);
        verify(colisService).assignerLivreurAuColis(5L, 9L);
    }

    // ----------------------------------------------------

    @Test
    void testFiltrerColis() {
        Page<ColisAvecZoneDTO> page = new PageImpl<>(Collections.emptyList());

        when(colisService.getColisFiltres(
                anyList(), eq(1L), eq("Casa"), anyList(), any(PageRequest.class)
        )).thenReturn(page);

        Page<ColisAvecZoneDTO> result = colisController.filtrerColis(
                Arrays.asList("Créé"),
                1L,
                "Casa",
                Arrays.asList("Haute"),
                0,
                10
        );

        assertEquals(page, result);
        verify(colisService).getColisFiltres(
                anyList(), eq(1L), eq("Casa"), anyList(), any(PageRequest.class)
        );
    }

    // ----------------------------------------------------

    @Test
    void testGetColisParNomZone() {
        List<ColisAvecZoneDTO> list = Collections.singletonList(new ColisAvecZoneDTO());

        when(colisService.getColisParNomZone("Zone1")).thenReturn(list);

        List<ColisAvecZoneDTO> result = colisController.getColisParNomZone("Zone1");

        assertEquals(list, result);
        verify(colisService).getColisParNomZone("Zone1");
    }

    // ----------------------------------------------------

    @Test
    void testGetColisParVille() {
        List<ColisAvecZoneDTO> list = Collections.singletonList(new ColisAvecZoneDTO());

        when(colisService.getColisParVille("Fès")).thenReturn(list);

        List<ColisAvecZoneDTO> result = colisController.getColisParVille("Fès");

        assertEquals(list, result);
        verify(colisService).getColisParVille("Fès");
    }

    // ----------------------------------------------------

    @Test
    void testGetNombreColisParLivreur() {
        when(colisService.getNombreTotalColisParLivreur(7L)).thenReturn(5L);

        long result = colisController.getNombreColisParLivreur(7L);

        assertEquals(5L, result);
        verify(colisService).getNombreTotalColisParLivreur(7L);
    }

    // ----------------------------------------------------

    @Test
    void testGetPoidsTotalColisParLivreur() {
        BigDecimal poids = BigDecimal.valueOf(140.5);

        when(colisService.getPoidsTotalParLivreur(8L)).thenReturn(poids);

        BigDecimal result = colisController.getPoidsTotalColisParLivreur(8L);

        assertEquals(poids, result);
        verify(colisService).getPoidsTotalParLivreur(8L);
    }
}
