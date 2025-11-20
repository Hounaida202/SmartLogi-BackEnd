package com.example.demo.DTO;

import com.example.demo.dto.ColisAvecZoneDTO;
import com.example.demo.dto.ZoneAvecColisDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ColisAvecZoneDTOTest {

    @Test
    void testGettersAndSetters() {
        ColisAvecZoneDTO dto = new ColisAvecZoneDTO();
        ZoneAvecColisDTO zone = new ZoneAvecColisDTO();

        dto.setDescription("Test desc");
        dto.setPoids(BigDecimal.valueOf(12.5));
        dto.setStatut("En cours");
        dto.setPriorite("Moyenne");
        dto.setIdLivreur(1L);
        dto.setIdClientExpediteur(2L);
        dto.setIdDestinataire(3L);
        dto.setIdZone(zone);
        dto.setVilleDestination("Rabat");

        assertEquals("Test desc", dto.getDescription());
        assertEquals(BigDecimal.valueOf(12.5), dto.getPoids());
        assertEquals("En cours", dto.getStatut());
        assertEquals("Moyenne", dto.getPriorite());
        assertEquals(1L, dto.getIdLivreur());
        assertEquals(2L, dto.getIdClientExpediteur());
        assertEquals(3L, dto.getIdDestinataire());
        assertEquals(zone, dto.getIdZone());
        assertEquals("Rabat", dto.getVilleDestination());
    }
}
