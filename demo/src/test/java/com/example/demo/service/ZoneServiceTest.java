package com.example.demo.service;

import com.example.demo.dto.ZoneDTO;
import com.example.demo.entity.Zone;
import com.example.demo.mapper.ZoneMapper;
import com.example.demo.repository.ZoneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ZoneServiceTest {

    @InjectMocks
    private ZoneService zoneService;

    @Mock
    private ZoneRepository zoneRepository;

    @Mock
    private ZoneMapper zoneMapper;

    private Zone zone;
    private ZoneDTO zoneDTO;

    @BeforeEach
    void setUp() {
        zone = new Zone();
        zone.setId(1L);
        zone.setNom("ZoneTest");

        zoneDTO = new ZoneDTO();
        zoneDTO.setNom("ZoneTest");

        when(zoneMapper.toEntity(zoneDTO)).thenReturn(zone);
        when(zoneMapper.toDTO(zone)).thenReturn(zoneDTO);
        when(zoneRepository.save(zone)).thenReturn(zone);
    }

    @Test
    void testSaveZone() {
        ZoneDTO resultat = zoneService.saveZone(zoneDTO);

        assertNotNull(resultat);
        assertEquals("ZoneTest", resultat.getNom());

        verify(zoneMapper).toEntity(zoneDTO);
        verify(zoneRepository).save(zone);
        verify(zoneMapper).toDTO(zone);
    }
}
