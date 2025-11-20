package com.example.demo.mapper;

import com.example.demo.dto.ColisDTO;
import com.example.demo.dto.UpdateStautColisDTO;
import com.example.demo.entity.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColisMapperTest {

    private final ColisMapper mapper = ColisMapper.INSTANCE;

    @Test
    void testToDTO() {
        Colis colis = new Colis();
        colis.setId(1L);
        colis.setDescription("Test");
        colis.setPoids(null);
        colis.setStatut("Créé");
        colis.setPriorite("Haute");

        Livreur livreur = new Livreur(); livreur.setId(10L);
        colis.setIdLivreur(livreur);

        ClientExpediteur client = new ClientExpediteur(); client.setId(20L);
        colis.setIdClientExpediteur(client);

        Destinataire destinataire = new Destinataire(); destinataire.setId(30L);
        colis.setIdDestinataire(destinataire);

        Zone zone = new Zone(); zone.setId(40L); zone.setNom("Zone1");
        colis.setIdZone(zone);

        ColisDTO dto = mapper.toDTO(colis);

        assertEquals("Test", dto.getDescription());
        assertEquals("Créé", dto.getStatut());
        assertEquals("Haute", dto.getPriorite());
        assertEquals(10L, dto.getIdLivreur());
        assertEquals(20L, dto.getIdClientExpediteur());
        assertEquals(30L, dto.getIdDestinataire());
        assertEquals(40L, dto.getIdZone());
        assertEquals("Zone1", dto.getNomZone());
    }

    @Test
    void testToEntity() {
        ColisDTO dto = new ColisDTO();
        dto.setIdLivreur(10L);
        dto.setIdClientExpediteur(20L);
        dto.setIdDestinataire(30L);
        dto.setIdZone(40L);
        dto.setDescription("Desc");
        dto.setStatut("Statut");
        dto.setPriorite("Priorité");

        Colis entity = mapper.toEntity(dto);

        assertEquals("Desc", entity.getDescription());
        assertEquals("Statut", entity.getStatut());
        assertEquals("Priorité", entity.getPriorite());
        assertEquals(10L, entity.getIdLivreur().getId());
        assertEquals(20L, entity.getIdClientExpediteur().getId());
        assertEquals(30L, entity.getIdDestinataire().getId());
        assertEquals(40L, entity.getIdZone().getId());
    }

    @Test
    void testUpdateStatutFromDTO() {
        Colis entity = new Colis();
        entity.setStatut("Ancien");

        UpdateStautColisDTO dto = new UpdateStautColisDTO();
        dto.setStatut("Nouveau");

        mapper.updateStatutFromDTO(dto, entity);
        assertEquals("Nouveau", entity.getStatut());

        // Test with null statut -> should not change
        dto.setStatut(null);
        mapper.updateStatutFromDTO(dto, entity);
        assertEquals("Nouveau", entity.getStatut());

        // Test with empty statut -> should not change
        dto.setStatut("");
        mapper.updateStatutFromDTO(dto, entity);
        assertEquals("Nouveau", entity.getStatut());
    }
}
