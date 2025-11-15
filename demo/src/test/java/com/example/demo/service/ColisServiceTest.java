package com.example.demo.service;

import com.example.demo.dto.ColisAvecZoneDTO;
import com.example.demo.dto.ColisDTO;
import com.example.demo.dto.UpdateStautColisDTO;
import com.example.demo.entity.ClientExpediteur;
import com.example.demo.entity.Colis;
import com.example.demo.entity.Destinataire;
import com.example.demo.entity.Livreur;
import com.example.demo.mapper.ColisMapper;
import com.example.demo.mapper.ColisZoneMapper;
import com.example.demo.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ColisServiceTest {

    @InjectMocks
    private ColisService colisService;

    @Mock
    private ColisRepository colisRepository;
    @Mock
    private ClientExpediteurRepository clientExpediteurRepository;
    @Mock
    private DestinataireRepository destinataireRepository;
    @Mock
    private ColisMapper colisMapper;
    @Mock
    private ColisZoneMapper coliszoneMapper;
    @Mock
    private LivreurRepository livreurRepository;

    private Colis colis;
    private ColisDTO colisDto;
    private ColisAvecZoneDTO colisAvecZoneDto;
    private ClientExpediteur client;
    private Destinataire destinataire;
    private Livreur livreur;

    @BeforeEach
    void setUp() {
        colis = new Colis();
        colisDto = new ColisDTO();
        colisAvecZoneDto = new ColisAvecZoneDTO();
        client = new ClientExpediteur();
        destinataire = new Destinataire();
        livreur = new Livreur();

        colisDto.setIdClientExpediteur(1L);
        colisDto.setIdDestinataire(2L);
        colisAvecZoneDto.setIdClientExpediteur(1L);
        colisAvecZoneDto.setIdDestinataire(2L);
    }

    // ===================== Test creerColisAvecZone =====================
    @Test
    void testCreerColisAvecZone_siIdsExistants() {
        // Arrange
        when(coliszoneMapper.toEntity(colisAvecZoneDto)).thenReturn(colis);
        when(clientExpediteurRepository.findById(1L)).thenReturn(Optional.of(client));
        when(destinataireRepository.findById(2L)).thenReturn(Optional.of(destinataire));
        when(colisRepository.save(colis)).thenReturn(colis);

        // Act
        ColisAvecZoneDTO resultat = colisService.creerColisAvecZone(colisAvecZoneDto);

        // Assert
        assertNotNull(resultat);
        assertEquals(client, colis.getIdClientExpediteur());
        assertEquals(destinataire, colis.getIdDestinataire());
        assertNull(colis.getIdLivreur());
        verify(colisRepository).save(colis);
    }

    // ===================== Test saveColis =====================
    @Test
    void testSaveColis_siColisValide() {
        when(colisMapper.toEntity(colisDto)).thenReturn(colis);
        when(colisRepository.save(colis)).thenReturn(colis);
        when(colisMapper.toDTO(colis)).thenReturn(colisDto);

        ColisDTO resultat = colisService.saveColis(colisDto);

        assertNotNull(resultat);
        assertEquals(colisDto, resultat);
        verify(colisMapper).toEntity(colisDto);
        verify(colisRepository).save(colis);
        verify(colisMapper).toDTO(colis);
    }

    // ===================== Test getColisByClientAndStatut =====================
    @Test
    void testGetColisByClientAndStatut() {
        Page<Colis> pageColis = new PageImpl<>(List.of(colis));
        when(colisRepository.findByIdClientExpediteur_IdAndStatutIn(eq(1L), anyList(), any(Pageable.class)))
                .thenReturn(pageColis);
        when(colisMapper.toDTO(colis)).thenReturn(colisDto);

        Page<ColisDTO> resultat = colisService.getColisByClientAndStatut(1L, Pageable.unpaged());

        assertEquals(1, resultat.getTotalElements());
        verify(colisRepository).findByIdClientExpediteur_IdAndStatutIn(eq(1L), anyList(), any(Pageable.class));
    }

    // ===================== Test trouverParId =====================
    @Test
    void testTrouverParId() {
        when(colisRepository.findById(1L)).thenReturn(Optional.of(colis));

        Optional<Colis> resultat = colisService.trouverParId(1L);

        assertTrue(resultat.isPresent());
        assertEquals(colis, resultat.get());
    }

    // ===================== Test getStatutColisParDestinataire =====================
    @Test
    void testGetStatutColisParDestinataire() {
        colis.setStatut("crée");
        when(colisRepository.findByIdDestinataire_Id(2L)).thenReturn(List.of(colis));

        List<String> statuts = colisService.getStatutColisParDestinataire(2L);

        assertEquals(List.of("crée"), statuts);
    }

    // ===================== Test getColisAvecZoneParLivreur =====================
    @Test
    void testGetColisAvecZoneParLivreur() {
        when(colisRepository.findByIdLivreur_Id(1L)).thenReturn(List.of(colis));
        when(coliszoneMapper.toDTO(colis)).thenReturn(colisAvecZoneDto);

        List<ColisAvecZoneDTO> resultat = colisService.getColisAvecZoneParLivreur(1L);

        assertEquals(1, resultat.size());
        assertEquals(colisAvecZoneDto, resultat.get(0));
    }

    // ===================== Test updateStautColis =====================
    @Test
    void testUpdateStautColis() {
        UpdateStautColisDTO dto = new UpdateStautColisDTO();
        dto.setStatut("livré");

        when(colisRepository.findById(1L)).thenReturn(Optional.of(colis));
        doNothing().when(colisMapper).updateStatutFromDTO(dto, colis);
        when(colisRepository.save(colis)).thenReturn(colis);
        when(colisMapper.toDTO(colis)).thenReturn(colisDto);

//        ColisDTO resultat = colisService.updateStautColis(1L, dto);

//        assertNotNull(resultat);
        verify(colisMapper).updateStatutFromDTO(dto, colis);
        verify(colisRepository).save(colis);
    }

    // ===================== Test getColisSansLivreur =====================
    @Test
    void testGetColisSansLivreur() {
        when(colisRepository.findByIdLivreurIsNull()).thenReturn(List.of(colis));
        when(coliszoneMapper.toDTO(colis)).thenReturn(colisAvecZoneDto);

        List<ColisAvecZoneDTO> resultat = colisService.getColisSansLivreur();

        assertEquals(1, resultat.size());
        assertEquals(colisAvecZoneDto, resultat.get(0));
    }

    // ===================== Test assignerLivreurAuColis =====================
    @Test
    void testAssignerLivreurAuColis() {
        when(colisRepository.findById(1L)).thenReturn(Optional.of(colis));
        when(livreurRepository.findById(2L)).thenReturn(Optional.of(livreur));
        when(colisRepository.save(colis)).thenReturn(colis);
        when(coliszoneMapper.toDTO(colis)).thenReturn(colisAvecZoneDto);

        ColisAvecZoneDTO resultat = colisService.assignerLivreurAuColis(1L, 2L);

        assertEquals(colisAvecZoneDto, resultat);
        assertEquals(livreur, colis.getIdLivreur());
        verify(colisRepository).save(colis);
    }

    // ===================== Test getColisFiltres =====================
    @Test
    void testGetColisFiltres() {
        Page<Colis> pageColis = new PageImpl<>(List.of(colis));
        when(colisRepository
                .findByStatutInAndIdZone_IdAndVilleDestinationContainingIgnoreCaseAndPrioriteIn(
                        anyList(), anyLong(), anyString(), anyList(), any(Pageable.class)))
                .thenReturn(pageColis);
        when(coliszoneMapper.toDTO(colis)).thenReturn(colisAvecZoneDto);

        Page<ColisAvecZoneDTO> resultat = colisService.getColisFiltres(
                List.of("crée"), 1L, "ville", List.of("p1"), Pageable.unpaged()
        );

        assertEquals(1, resultat.getTotalElements());
    }

    // ===================== Test getColisParNomZone =====================
    @Test
    void testGetColisParNomZone() {
        when(colisRepository.findByIdZone_NomIgnoreCase("zone")).thenReturn(List.of(colis));
        when(coliszoneMapper.toDTO(colis)).thenReturn(colisAvecZoneDto);

        List<ColisAvecZoneDTO> resultat = colisService.getColisParNomZone("zone");

        assertEquals(1, resultat.size());
    }

    // ===================== Test getColisParVille =====================
    @Test
    void testGetColisParVille() {
        when(colisRepository.findByVilleDestinationIgnoreCase("ville")).thenReturn(List.of(colis));
        when(coliszoneMapper.toDTO(colis)).thenReturn(colisAvecZoneDto);

        List<ColisAvecZoneDTO> resultat = colisService.getColisParVille("ville");

        assertEquals(1, resultat.size());
    }

    // ===================== Test getNombreTotalColisParLivreur =====================
    @Test
    void testGetNombreTotalColisParLivreur() {
        when(colisRepository.countByIdLivreur_Id(1L)).thenReturn(5L);

        long resultat = colisService.getNombreTotalColisParLivreur(1L);

        assertEquals(5L, resultat);
    }

    // ===================== Test getPoidsTotalParLivreur =====================
    @Test
    void testGetPoidsTotalParLivreur() {
        when(colisRepository.getPoidsTotalParLivreur(1L)).thenReturn(BigDecimal.valueOf(50));

        BigDecimal resultat = colisService.getPoidsTotalParLivreur(1L);

        assertEquals(BigDecimal.valueOf(50), resultat);
    }

}
