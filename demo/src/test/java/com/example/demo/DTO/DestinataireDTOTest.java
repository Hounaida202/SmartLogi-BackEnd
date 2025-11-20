package com.example.demo.DTO;

import com.example.demo.dto.DestinataireDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DestinataireDTOTest {

    @Test
    void testGettersAndSetters() {
        DestinataireDTO dto = new DestinataireDTO();

        dto.setNom("Doe");
        dto.setPrenom("John");
        dto.setEmail("john.doe@example.com");
        dto.setTelephone("0612345678");
        dto.setAdresse("123 Rue Exemple");

        assertEquals("Doe", dto.getNom());
        assertEquals("John", dto.getPrenom());
        assertEquals("john.doe@example.com", dto.getEmail());
        assertEquals("0612345678", dto.getTelephone());
        assertEquals("123 Rue Exemple", dto.getAdresse());
    }
}
