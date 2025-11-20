package com.example.demo.DTO;

import com.example.demo.dto.ColisDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ColisDTOTest {

    @Test
    void testGettersAndSetters() {
        ColisDTO dto = new ColisDTO();

        dto.setDescription("Test description");
        dto.setPoids(BigDecimal.valueOf(10.5));
        dto.setStatut("Créé");
        dto.setPriorite("Haute");
        dto.setIdLivreur(1L);
        dto.setIdClientExpediteur(2L);
        dto.setIdDestinataire(3L);
        dto.setIdZone(4L);
        dto.setVilleDestination("Casablanca");
        dto.setNomZone("Zone1");

        assertEquals("Test description", dto.getDescription());
        assertEquals(BigDecimal.valueOf(10.5), dto.getPoids());
        assertEquals("Créé", dto.getStatut());
        assertEquals("Haute", dto.getPriorite());
        assertEquals(1L, dto.getIdLivreur());
        assertEquals(2L, dto.getIdClientExpediteur());
        assertEquals(3L, dto.getIdDestinataire());
        assertEquals(4L, dto.getIdZone());
        assertEquals("Casablanca", dto.getVilleDestination());
        assertEquals("Zone1", dto.getNomZone());
    }
}
