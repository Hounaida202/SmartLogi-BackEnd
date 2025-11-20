package com.example.demo.mapper;

import com.example.demo.dto.ColisAvecZoneDTO;
import com.example.demo.entity.Colis;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColisZoneMapperTest {

    private final ColisZoneMapper mapper = new ColisZoneMapperImpl(); // MapStruct génère Impl automatiquement

    @Test
    void testToDTO() {
        Colis colis = new Colis();
        colis.setDescription("Test");
        colis.setPoids(null);

        ColisAvecZoneDTO dto = mapper.toDTO(colis);

        assertNotNull(dto);
        assertEquals("Test", dto.getDescription());
        assertNull(dto.getPoids());
        // idLivreur, idDestinataire, idClientExpediteur sont ignorés donc restent null
        assertNull(dto.getIdLivreur());
        assertNull(dto.getIdDestinataire());
        assertNull(dto.getIdClientExpediteur());
    }

    @Test
    void testToEntity() {
        ColisAvecZoneDTO dto = new ColisAvecZoneDTO();
        dto.setDescription("Desc");

        Colis colis = mapper.toEntity(dto);

        assertNotNull(colis);
        assertEquals("Desc", colis.getDescription());
        // idLivreur, idDestinataire, idClientExpediteur sont ignorés donc restent null
        assertNull(colis.getIdLivreur());
        assertNull(colis.getIdDestinataire());
        assertNull(colis.getIdClientExpediteur());
    }
}
