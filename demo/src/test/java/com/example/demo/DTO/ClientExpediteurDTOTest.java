package com.example.demo.DTO;

import com.example.demo.dto.ClientExpediteurDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientExpediteurDTOTest {

    @Test
    void testGettersAndSetters() {
        ClientExpediteurDTO dto = new ClientExpediteurDTO();

        dto.setNom("Khalid");
        dto.setPrenom("Youssef");
        dto.setEmail("khalid.youssef@example.com");
        dto.setTelephone("0612345678");
        dto.setAdresse("456 Rue Exemple");

        assertEquals("Khalid", dto.getNom());
        assertEquals("Youssef", dto.getPrenom());
        assertEquals("khalid.youssef@example.com", dto.getEmail());
        assertEquals("0612345678", dto.getTelephone());
        assertEquals("456 Rue Exemple", dto.getAdresse());
    }
}
