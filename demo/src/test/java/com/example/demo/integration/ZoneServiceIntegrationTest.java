package com.example.demo.integration;

import com.example.demo.dto.ZoneDTO;
import com.example.demo.entity.Zone;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.ZoneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class ZoneServiceIntegrationTest {

    @Autowired
    private ZoneService zoneService;

    @Autowired
    private ZoneRepository zoneRepository;

    @BeforeEach
    void setUp() {
        zoneRepository.deleteAll();
    }

    @Test
    void testSaveZone_shouldSaveSuccessfully() {
        ZoneDTO dto = new ZoneDTO();
        dto.setNom("Casablanca Centre");
        dto.setCodePostal("20000");

        ZoneDTO result = zoneService.saveZone(dto);

        assertNotNull(result);
        assertEquals("Casablanca Centre", result.getNom());
        assertEquals("20000", result.getCodePostal());

        List<Zone> zones = zoneRepository.findAll();
        assertEquals(1, zones.size());
        assertEquals("Casablanca Centre", zones.get(0).getNom());
    }

    @Test
    void testSaveZone_shouldSaveMultipleZones() {

        ZoneDTO dto1 = new ZoneDTO();
        dto1.setNom("Rabat Agdal");
        dto1.setCodePostal("10000");

        ZoneDTO dto2 = new ZoneDTO();
        dto2.setNom("Marrakech Guéliz");
        dto2.setCodePostal("40000");


        zoneService.saveZone(dto1);
        zoneService.saveZone(dto2);


        List<Zone> zones = zoneRepository.findAll();
        assertEquals(2, zones.size());
    }

    @Test
    void testSaveZone_canFindByNom() {
        ZoneDTO dto = new ZoneDTO();
        dto.setNom("Fès Médina");
        dto.setCodePostal("30000");

        zoneService.saveZone(dto);

        Optional<Zone> found = zoneRepository.findByNom("Fès Médina");
        assertTrue(found.isPresent());
        assertEquals("Fès Médina", found.get().getNom());
        assertEquals("30000", found.get().getCodePostal());
    }
}