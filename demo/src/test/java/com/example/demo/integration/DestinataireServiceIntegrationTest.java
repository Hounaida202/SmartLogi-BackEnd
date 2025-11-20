package com.example.demo.integration;

import com.example.demo.dto.DestinataireDTO;
import com.example.demo.entity.Destinataire;
import com.example.demo.repository.DestinataireRepository;
import com.example.demo.service.DestinataireService;
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
class DestinataireServiceIntegrationTest {

    @Autowired
    private DestinataireService destinataireService;

    @Autowired
    private DestinataireRepository destinataireRepository;

    @BeforeEach
    void setUp() {
        destinataireRepository.deleteAll();
    }

    @Test
    void testSaveDestinataire_shouldSaveSuccessfully() {
        DestinataireDTO dto = new DestinataireDTO();
        dto.setNom("Benali");
        dto.setPrenom("Ahmed");
        dto.setEmail("ahmed.benali@example.com");
        dto.setTelephone("0661234567");
        dto.setAdresse("45 Avenue Hassan II");

        DestinataireDTO result = destinataireService.saveDestinataire(dto);

        assertNotNull(result);
        assertEquals("Benali", result.getNom());
        assertEquals("Ahmed", result.getPrenom());
        assertEquals("ahmed.benali@example.com", result.getEmail());

        List<Destinataire> destinataires = destinataireRepository.findAll();
        assertEquals(1, destinataires.size());
        assertEquals("Benali", destinataires.get(0).getNom());
    }

    @Test
    void testSaveDestinataire_shouldSaveMultipleDestinataires() {
        DestinataireDTO dto1 = new DestinataireDTO();
        dto1.setNom("Alami");
        dto1.setPrenom("Fatima");
        dto1.setEmail("fatima.alami@example.com");
        dto1.setTelephone("0671234567");
        dto1.setAdresse("10 Rue Mohammed V");

        DestinataireDTO dto2 = new DestinataireDTO();
        dto2.setNom("Tazi");
        dto2.setPrenom("Youssef");
        dto2.setEmail("youssef.tazi@example.com");
        dto2.setTelephone("0681234567");
        dto2.setAdresse("20 Boulevard Zerktouni");
        destinataireService.saveDestinataire(dto1);
        destinataireService.saveDestinataire(dto2);

        List<Destinataire> destinataires = destinataireRepository.findAll();
        assertEquals(2, destinataires.size());
    }

    @Test
    void testSaveDestinataire_withNullFields() {
        DestinataireDTO dto = new DestinataireDTO();
        dto.setNom("Mouhib");
        dto.setPrenom("Karim");

        DestinataireDTO result = destinataireService.saveDestinataire(dto);

        assertNotNull(result);
        assertEquals("Mouhib", result.getNom());
        assertEquals("Karim", result.getPrenom());
        assertNull(result.getEmail());
    }
}