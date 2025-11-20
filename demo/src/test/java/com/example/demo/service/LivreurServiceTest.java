package com.example.demo.service;

import com.example.demo.dto.LivreurDTO;
import com.example.demo.entity.Livreur;
import com.example.demo.entity.Zone;
import com.example.demo.mapper.LivreurMapper;
import com.example.demo.repository.LivreurRepository;
import com.example.demo.repository.ZoneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LivreurServiceTest {

    @InjectMocks
    private LivreurService livreurService; // Classe que l’on teste

    @Mock
    private LivreurRepository livreurRepository;

    @Mock
    private ZoneRepository zoneRepository;

    @Mock
    private LivreurMapper livreurMapper;

    private LivreurDTO dto;
    private Livreur livreur;
    private Zone zone;

    @BeforeEach
    void setUp() {
        dto = new LivreurDTO();
        dto.setNom("Ahmed");
        dto.setZoneAssignee("Zone1");

        zone = new Zone();
        zone.setId(1L);
        zone.setNom("Zone1");

        livreur = new Livreur();
        livreur.setNom("Ahmed");
        livreur.setZoneAssignee(zone);
    }

    @Test
    void testFindByNom() {
        when(zoneRepository.findByNom("Zone1")).thenReturn(Optional.of(zone));

        Optional<Zone> resultat = livreurService.findByNom("Zone1");

        assertTrue(resultat.isPresent());
        assertEquals("Zone1", resultat.get().getNom());
        verify(zoneRepository).findByNom("Zone1");
    }

    @Test
    void testSaveLivreur() {
        when(livreurMapper.toEntity(dto)).thenReturn(livreur);
        when(zoneRepository.findByNom("Zone1")).thenReturn(Optional.of(zone));
        when(livreurRepository.save(livreur)).thenReturn(livreur);
        when(livreurMapper.toDTO(livreur)).thenReturn(dto);

        LivreurDTO resultat = livreurService.saveLivreur(dto);

        assertNotNull(resultat);
        assertEquals("Ahmed", resultat.getNom());
        assertEquals("Zone1", resultat.getZoneAssignee());

        verify(livreurMapper).toEntity(dto);
        verify(zoneRepository).findByNom("Zone1");
        verify(livreurRepository).save(livreur);
        verify(livreurMapper).toDTO(livreur);
    }
}
