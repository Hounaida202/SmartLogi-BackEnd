package com.example.demo.mapper;

import com.example.demo.dto.ZoneAvecColisDTO;
import com.example.demo.entity.Zone;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZoneColisMapperTest {

    private final ZoneColisMapper mapper = new ZoneColisMapperImpl();

    @Test
    void testToDTO() {
        Zone zone = new Zone();
        zone.setNom("Zone1");

        ZoneAvecColisDTO dto = mapper.toDTO(zone);

        assertNotNull(dto);
        assertEquals("Zone1", dto.getNom());
    }

    @Test
    void testToEntity() {
        ZoneAvecColisDTO dto = new ZoneAvecColisDTO();
        dto.setNom("Zone1");

        Zone zone = mapper.toEntity(dto);

        assertNotNull(zone);
        assertEquals("Zone1", zone.getNom());
    }
}
