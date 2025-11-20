package com.example.demo.integration;

import com.example.demo.constants.ColisConstants;
import com.example.demo.dto.ColisAvecZoneDTO;
import com.example.demo.dto.UpdateStautColisDTO;
import com.example.demo.dto.ZoneAvecColisDTO;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.ColisService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class ColisServiceIntegrationTest {

    @Autowired
    private ColisService colisService;

    @Autowired
    private ColisRepository colisRepository;

    @Autowired
    private ClientExpediteurRepository clientExpediteurRepository;

    @Autowired
    private DestinataireRepository destinataireRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private LivreurRepository livreurRepository;

    private ClientExpediteur testClient;
    private Destinataire testDestinataire;
    private Zone testZone;
    private Livreur testLivreur;

    @BeforeEach
    void setUp() {
        colisRepository.deleteAll();
        clientExpediteurRepository.deleteAll();
        destinataireRepository.deleteAll();
        zoneRepository.deleteAll();
        livreurRepository.deleteAll();

        testClient = new ClientExpediteur();
        testClient.setNom("Client Test");
        testClient.setPrenom("Test");
        testClient = clientExpediteurRepository.save(testClient);

        testDestinataire = new Destinataire();
        testDestinataire.setNom("Dest Test");
        testDestinataire.setPrenom("Test");
        testDestinataire = destinataireRepository.save(testDestinataire);

        testZone = new Zone();
        testZone.setNom("Casablanca Centre");
        testZone.setCodePostal("20000");
        testZone = zoneRepository.save(testZone);

        testLivreur = new Livreur();
        testLivreur.setNom("Livreur Test");
        testLivreur.setPrenom("Test");
        testLivreur.setZoneAssignee(testZone);
        testLivreur = livreurRepository.save(testLivreur);
    }

    @Test
    void testCreerColisAvecZone_shouldCreateSuccessfully() {
        ColisAvecZoneDTO dto = new ColisAvecZoneDTO();
        dto.setDescription("Colis Test");
        dto.setPoids(new BigDecimal("5.00"));
        dto.setStatut(ColisConstants.Statut.CREE);
        dto.setPriorite(ColisConstants.Priorite.P1);
        dto.setVilleDestination("Casablanca");
        dto.setIdClientExpediteur(testClient.getId());
        dto.setIdDestinataire(testDestinataire.getId());

        ZoneAvecColisDTO zoneDto = new ZoneAvecColisDTO();
        zoneDto.setNom(testZone.getNom());
        zoneDto.setCodePostal(testZone.getCodePostal());
        dto.setIdZone(zoneDto);

        ColisAvecZoneDTO result = colisService.creerColisAvecZone(dto);

        assertNotNull(result);
        List<Colis> colis = colisRepository.findAll();
        assertEquals(1, colis.size());
        assertEquals("Colis Test", colis.get(0).getDescription());
        assertNull(colis.get(0).getIdLivreur());
    }

    @Test
    void testGetStatutColisParDestinataire_shouldReturnStatuts() {
        createTestColis(ColisConstants.Statut.CREE);
        createTestColis(ColisConstants.Statut.LIVRE);

        List<String> statuts = colisService.getStatutColisParDestinataire(testDestinataire.getId());

        assertEquals(2, statuts.size());
        assertTrue(statuts.contains(ColisConstants.Statut.CREE));
        assertTrue(statuts.contains(ColisConstants.Statut.LIVRE));
    }

    @Test
    void testGetColisAvecZoneParLivreur_shouldReturnColisList() {
        Colis colis1 = createTestColis(ColisConstants.Statut.EN_TRANSIT);
        colis1.setIdLivreur(testLivreur);
        colisRepository.save(colis1);

        Colis colis2 = createTestColis(ColisConstants.Statut.COLLECTE);
        colis2.setIdLivreur(testLivreur);
        colisRepository.save(colis2);

        List<ColisAvecZoneDTO> result = colisService.getColisAvecZoneParLivreur(testLivreur.getId());

        assertEquals(2, result.size());
    }

    @Test
    void testUpdateStautColis_shouldUpdateStatut() {
        Colis colis = createTestColis(ColisConstants.Statut.CREE);
        UpdateStautColisDTO dto = new UpdateStautColisDTO();
        dto.setStatut(ColisConstants.Statut.LIVRE);

        colisService.updateStautColis(colis.getId(), dto);

        Colis updated = colisRepository.findById(colis.getId()).get();
        assertEquals(ColisConstants.Statut.LIVRE, updated.getStatut());
    }

    @Test
    void testGetColisSansLivreur_shouldReturnUnassignedColis() {
        createTestColis(ColisConstants.Statut.CREE);
        createTestColis(ColisConstants.Statut.CREE);

        Colis colisAvecLivreur = createTestColis(ColisConstants.Statut.EN_TRANSIT);
        colisAvecLivreur.setIdLivreur(testLivreur);
        colisRepository.save(colisAvecLivreur);

        List<ColisAvecZoneDTO> result = colisService.getColisSansLivreur();

        assertEquals(2, result.size());
    }

    @Test
    void testAssignerLivreurAuColis_shouldAssignLivreur() {
        Colis colis = createTestColis(ColisConstants.Statut.CREE);

        ColisAvecZoneDTO result = colisService.assignerLivreurAuColis(colis.getId(), testLivreur.getId());

        assertNotNull(result);
        Colis updated = colisRepository.findById(colis.getId()).get();
        assertNotNull(updated.getIdLivreur());
        assertEquals(testLivreur.getId(), updated.getIdLivreur().getId());
    }

    @Test
    void testGetColisFiltres_shouldFilterCorrectly() {
        createTestColis(ColisConstants.Statut.CREE);
        createTestColis(ColisConstants.Statut.LIVRE);

        Pageable pageable = PageRequest.of(0, 10);

        Page<ColisAvecZoneDTO> result = colisService.getColisFiltres(
                List.of(ColisConstants.Statut.CREE),
                testZone.getId(),
                "Casablanca",
                List.of(ColisConstants.Priorite.P1),
                pageable
        );

        assertTrue(result.getTotalElements() >= 1);
    }

    @Test
    void testGetColisParNomZone_shouldReturnColisByZone() {
        createTestColis(ColisConstants.Statut.CREE);

        List<ColisAvecZoneDTO> result = colisService.getColisParNomZone("Casablanca Centre");

        assertEquals(1, result.size());
    }

    @Test
    void testGetColisParVille_shouldReturnColisByCity() {
        createTestColis(ColisConstants.Statut.CREE);

        List<ColisAvecZoneDTO> result = colisService.getColisParVille("Casablanca");

        assertEquals(1, result.size());
    }

    @Test
    void testGetNombreTotalColisParLivreur_shouldReturnCount() {
        Colis colis1 = createTestColis(ColisConstants.Statut.EN_TRANSIT);
        colis1.setIdLivreur(testLivreur);
        colisRepository.save(colis1);

        Colis colis2 = createTestColis(ColisConstants.Statut.LIVRE);
        colis2.setIdLivreur(testLivreur);
        colisRepository.save(colis2);

        long count = colisService.getNombreTotalColisParLivreur(testLivreur.getId());

        assertEquals(2, count);
    }

    @Test
    void testGetPoidsTotalParLivreur_shouldReturnTotalWeight() {
        Colis colis1 = createTestColisWithWeight(
                ColisConstants.Statut.EN_TRANSIT,
                new BigDecimal("5.00")
        );
        colis1.setIdLivreur(testLivreur);
        colisRepository.save(colis1);

        Colis colis2 = createTestColisWithWeight(
                ColisConstants.Statut.LIVRE,
                new BigDecimal("3.50")
        );
        colis2.setIdLivreur(testLivreur);
        colisRepository.save(colis2);

        BigDecimal totalPoids = colisService.getPoidsTotalParLivreur(testLivreur.getId());

        assertEquals(0, new BigDecimal("8.50").compareTo(totalPoids));
    }


    private Colis createTestColis(String statut) {
        return createTestColisWithPriorite(statut, ColisConstants.Priorite.P1);
    }


    private Colis createTestColisWithWeight(String statut, BigDecimal poids) {
        Colis colis = new Colis();
        colis.setDescription("Test Colis");
        colis.setPoids(poids);
        colis.setStatut(statut);
        colis.setPriorite(ColisConstants.Priorite.P2);
        colis.setVilleDestination("Casablanca");
        colis.setIdClientExpediteur(testClient);
        colis.setIdDestinataire(testDestinataire);
        colis.setIdZone(testZone);
        return colisRepository.save(colis);
    }


    private Colis createTestColisWithPriorite(String statut, String priorite) {
        Colis colis = new Colis();
        colis.setDescription("Test Colis");
        colis.setPoids(new BigDecimal("5.00"));
        colis.setStatut(statut);
        colis.setPriorite(priorite);
        colis.setVilleDestination("Casablanca");
        colis.setIdClientExpediteur(testClient);
        colis.setIdDestinataire(testDestinataire);
        colis.setIdZone(testZone);
        return colisRepository.save(colis);
    }
}