package com.example.demo.service;

import com.example.demo.dto.DestinataireDTO;
import com.example.demo.entity.Destinataire;
import com.example.demo.mapper.DestinataireMapper;
import com.example.demo.repository.DestinataireRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DestinataireServiceTest {

    @InjectMocks
    private DestinataireService destinataireService;

    @Mock
    private DestinataireRepository destinataireRepository;
    @Mock
    private DestinataireMapper destinataireMapper;

    private DestinataireDTO dto;
    private Destinataire destinataire;

    @BeforeEach
    void setUp() {
        dto = new DestinataireDTO();
        dto.setNom("Dupont");
        dto.setPrenom("Jean");

        destinataire = new Destinataire();
        destinataire.setNom("Dupont");
        destinataire.setPrenom("Jean");
        destinataire.setId(1L);
    }

    @Test
    void testSaveDestinataire() {
        when(destinataireMapper.toEntity(dto)).thenReturn(destinataire);
        when(destinataireMapper.toDTO(destinataire)).thenReturn(dto);
        when(destinataireRepository.save(destinataire)).thenReturn(destinataire);

        DestinataireDTO resultat = destinataireService.saveDestinataire(dto);

        assertNotNull(resultat);
        assertEquals("Dupont", resultat.getNom());
        assertEquals("Jean", resultat.getPrenom());

        verify(destinataireMapper).toEntity(dto);
        verify(destinataireRepository).save(destinataire);
        verify(destinataireMapper).toDTO(destinataire);
    }
}
