package com.example.demo.service;

import com.example.demo.dto.ClientExpediteurDTO;
import com.example.demo.entity.ClientExpediteur;
import com.example.demo.mapper.ClientExpediteurMapper;
import com.example.demo.repository.ClientExpediteurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientExpediteurServiceTest {

    @InjectMocks
    private ClientExpediteurService clientService;

    @Mock
    private ClientExpediteurRepository clientRepository;

    @Mock
    private ClientExpediteurMapper clientMapper;

    private ClientExpediteurDTO clientDTO;
    private ClientExpediteur clientEntity;

    @BeforeEach
    void setUp() {
        clientDTO = new ClientExpediteurDTO();
        clientDTO.setNom("Honaida");

        clientEntity = new ClientExpediteur();
        clientEntity.setNom("Honaida");
    }

    @Test
    void testSaveUser() {
        when(clientMapper.toEntity(clientDTO)).thenReturn(clientEntity);

        when(clientRepository.save(clientEntity)).thenReturn(clientEntity);

        when(clientMapper.toDTO(clientEntity)).thenReturn(clientDTO);
        ClientExpediteurDTO resultat = clientService.saveUser(clientDTO);
        assertNotNull(resultat);
        assertEquals("Honaida", resultat.getNom());

        verify(clientMapper).toEntity(clientDTO);
        verify(clientRepository).save(clientEntity);
        verify(clientMapper).toDTO(clientEntity);
    }

    @Test
    void testGetClientsParNom() {
        List<ClientExpediteur> clientsList = List.of(clientEntity);
        when(clientRepository.findByNomIgnoreCase("Honaida")).thenReturn(clientsList);
        when(clientMapper.toDTO(clientEntity)).thenReturn(clientDTO);
        List<ClientExpediteurDTO> resultat = clientService.getClientsParNom("Honaida");
        assertNotNull(resultat);
        assertEquals(1, resultat.size());
        assertEquals("Honaida", resultat.get(0).getNom());
        verify(clientRepository).findByNomIgnoreCase("Honaida");
        verify(clientMapper).toDTO(clientEntity);
    }
}
