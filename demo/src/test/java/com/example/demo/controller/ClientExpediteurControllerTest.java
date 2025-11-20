package com.example.demo.controller;

import com.example.demo.dto.ClientExpediteurDTO;
import com.example.demo.service.ClientExpediteurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientExpediteurControllerTest {

    @Mock
    private ClientExpediteurService clientExpediteurService;

    @InjectMocks
    private ClientExpediteurController clientExpediteurController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // ----------------------------------------------------

    @Test
    void testCreateUser() {
        ClientExpediteurDTO dto = new ClientExpediteurDTO();
        ClientExpediteurDTO savedDto = new ClientExpediteurDTO();

        when(clientExpediteurService.saveUser(dto)).thenReturn(savedDto);

        ResponseEntity<ClientExpediteurDTO> response = clientExpediteurController.createUser(dto);

        assertEquals(savedDto, response.getBody());
        verify(clientExpediteurService).saveUser(dto);
    }

    // ----------------------------------------------------

    @Test
    void testChercherClientsParNom() {
        List<ClientExpediteurDTO> list = Arrays.asList(new ClientExpediteurDTO(), new ClientExpediteurDTO());

        when(clientExpediteurService.getClientsParNom("Mohamed")).thenReturn(list);

        List<ClientExpediteurDTO> result = clientExpediteurController.chercherClientsParNom("Mohamed");

        assertEquals(list, result);
        verify(clientExpediteurService).getClientsParNom("Mohamed");
    }

    // ----------------------------------------------------

    @Test
    void testChercherClientsParNomEmpty() {
        when(clientExpediteurService.getClientsParNom("Inexistant")).thenReturn(Collections.emptyList());

        List<ClientExpediteurDTO> result = clientExpediteurController.chercherClientsParNom("Inexistant");

        assertTrue(result.isEmpty());
        verify(clientExpediteurService).getClientsParNom("Inexistant");
    }
}
