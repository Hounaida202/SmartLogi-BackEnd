package com.example.demo.controller;

import com.example.demo.dto.ZoneDTO;
import com.example.demo.service.ZoneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ZoneControllerTest {

    @Mock
    private ZoneService zoneService;

    @InjectMocks
    private ZoneController zoneController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // ----------------------------------------------------

    @Test
    void testCreateZone() {
        ZoneDTO dto = new ZoneDTO();
        ZoneDTO savedDto = new ZoneDTO();

        when(zoneService.saveZone(dto)).thenReturn(savedDto);

        ResponseEntity<ZoneDTO> response = zoneController.createZone(dto);

        assertEquals(savedDto, response.getBody());
        verify(zoneService).saveZone(dto);
    }
}
