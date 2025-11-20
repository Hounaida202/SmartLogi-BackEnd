package com.example.demo.integration;

import com.example.demo.dto.ClientExpediteurDTO;
import com.example.demo.entity.ClientExpediteur;
import com.example.demo.repository.ClientExpediteurRepository;
import com.example.demo.service.ClientExpediteurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class ClientExpediteurServiceIntegrationTest {

    @Autowired
    private ClientExpediteurService clientExpediteurService;

    @Autowired
    private ClientExpediteurRepository clientExpediteurRepository;

    @BeforeEach
    void setUp() {
        clientExpediteurRepository.deleteAll();
    }

    @Test
    void testSaveUser_shouldSaveClientExpediteur() {
        ClientExpediteurDTO dto = new ClientExpediteurDTO();
        dto.setNom("Dupont");
        dto.setPrenom("Jean");
        dto.setEmail("jean.dupont@example.com");
        dto.setTelephone("0612345678");
        dto.setAdresse("123 Rue de Paris");
        ClientExpediteurDTO result = clientExpediteurService.saveUser(dto);

        assertNotNull(result);
        assertEquals("Dupont", result.getNom());
        assertEquals("Jean", result.getPrenom());
        assertEquals("jean.dupont@example.com", result.getEmail());

        List<ClientExpediteur> clients = clientExpediteurRepository.findAll();
        assertEquals(1, clients.size());
        assertEquals("Dupont", clients.get(0).getNom());
    }

    @Test
    void testGetClientsParNom_shouldReturnClientsWithMatchingName() {
        ClientExpediteurDTO dto1 = new ClientExpediteurDTO();
        dto1.setNom("Martin");
        dto1.setPrenom("Pierre");
        dto1.setEmail("pierre.martin@example.com");
        dto1.setTelephone("0611111111");

        ClientExpediteurDTO dto2 = new ClientExpediteurDTO();
        dto2.setNom("Martin");
        dto2.setPrenom("Paul");
        dto2.setEmail("paul.martin@example.com");
        dto2.setTelephone("0622222222");

        ClientExpediteurDTO dto3 = new ClientExpediteurDTO();
        dto3.setNom("Durand");
        dto3.setPrenom("Marie");
        dto3.setEmail("marie.durand@example.com");
        dto3.setTelephone("0633333333");

        clientExpediteurService.saveUser(dto1);
        clientExpediteurService.saveUser(dto2);
        clientExpediteurService.saveUser(dto3);

        List<ClientExpediteurDTO> results = clientExpediteurService.getClientsParNom("Martin");

        assertEquals(2, results.size());
        assertTrue(results.stream().allMatch(c -> c.getNom().equals("Martin")));
    }

    @Test
    void testGetClientsParNom_shouldReturnEmptyListWhenNoMatch() {
        ClientExpediteurDTO dto = new ClientExpediteurDTO();
        dto.setNom("Dupont");
        dto.setPrenom("Jean");
        dto.setEmail("jean.dupont@example.com");
        dto.setTelephone("0612345678");

        clientExpediteurService.saveUser(dto);

        List<ClientExpediteurDTO> results = clientExpediteurService.getClientsParNom("Inexistant");

        assertTrue(results.isEmpty());
    }
}