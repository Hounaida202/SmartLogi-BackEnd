package com.example.demo.integration;

import com.example.demo.dto.LivreurDTO;
import com.example.demo.entity.Livreur;
import com.example.demo.entity.Zone;
import com.example.demo.repository.LivreurRepository;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.LivreurService;
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
class LivreurServiceIntegrationTest {

    @Autowired
    private LivreurService livreurService;

    @Autowired
    private LivreurRepository livreurRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    private Zone testZone;

    @BeforeEach
    void setUp() {
        livreurRepository.deleteAll();
        zoneRepository.deleteAll();

        testZone = new Zone();
        testZone.setNom("Casablanca Centre");
        testZone.setCodePostal("20000");
        testZone = zoneRepository.save(testZone);
    }

    @Test
    void testSaveLivreur_shouldSaveWithZone() {
        LivreurDTO dto = new LivreurDTO();
        dto.setNom("Aziz");
        dto.setPrenom("Mohammed");
        dto.setTelephone("0612345678");
        dto.setVehicule("Moto");
        dto.setZoneAssignee("Casablanca Centre");

        LivreurDTO result = livreurService.saveLivreur(dto);

        assertNotNull(result);
        assertEquals("Aziz", result.getNom());
        assertEquals("Mohammed", result.getPrenom());
        assertEquals("Casablanca Centre", result.getZoneAssignee());

        List<Livreur> livreurs = livreurRepository.findAll();
        assertEquals(1, livreurs.size());
        assertEquals("Aziz", livreurs.get(0).getNom());
        assertNotNull(livreurs.get(0).getZoneAssignee());
        assertEquals("Casablanca Centre", livreurs.get(0).getZoneAssignee().getNom());
    }

    @Test
    void testSaveLivreur_shouldThrowExceptionWhenZoneNotFound() {
        LivreurDTO dto = new LivreurDTO();
        dto.setNom("Hassan");
        dto.setPrenom("Ali");
        dto.setTelephone("0623456789");
        dto.setVehicule("Voiture");
        dto.setZoneAssignee("Zone Inexistante");

        assertThrows(RuntimeException.class, () -> {
            livreurService.saveLivreur(dto);
        });
    }

    @Test
    void testSaveLivreur_shouldSaveMultipleLivreursInSameZone() {
        LivreurDTO dto1 = new LivreurDTO();
        dto1.setNom("Amrani");
        dto1.setPrenom("Khalid");
        dto1.setTelephone("0611111111");
        dto1.setVehicule("Moto");
        dto1.setZoneAssignee("Casablanca Centre");

        LivreurDTO dto2 = new LivreurDTO();
        dto2.setNom("Berrada");
        dto2.setPrenom("Omar");
        dto2.setTelephone("0622222222");
        dto2.setVehicule("Voiture");
        dto2.setZoneAssignee("Casablanca Centre");

        livreurService.saveLivreur(dto1);
        livreurService.saveLivreur(dto2);

        List<Livreur> livreurs = livreurRepository.findAll();
        assertEquals(2, livreurs.size());
        assertTrue(livreurs.stream().allMatch(l ->
                l.getZoneAssignee().getNom().equals("Casablanca Centre")));
    }

    @Test
    void testFindByNom_shouldReturnZoneWhenExists() {
        var result = livreurService.findByNom("Casablanca Centre");

        assertTrue(result.isPresent());
        assertEquals("Casablanca Centre", result.get().getNom());
    }

    @Test
    void testFindByNom_shouldReturnEmptyWhenNotExists() {
        var result = livreurService.findByNom("Zone Inexistante");

        assertTrue(result.isEmpty());
    }
}