package com.example.demo.mapper;

import com.example.demo.dto.ZoneDTO;
import com.example.demo.entity.Zone;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZoneMapperTest {

    private final ZoneMapper mapper = ZoneMapper.INSTANCE;

    @Test
    void testToDTO() {
        Zone zone = new Zone();
        zone.setNom("Zone1");

        ZoneDTO dto = mapper.toDTO(zone);

        assertNotNull(dto);
        assertEquals("Zone1", dto.getNom());
    }

    @Test
    void testToEntity() {
        ZoneDTO dto = new ZoneDTO();
        dto.setNom("Zone1");

        Zone zone = mapper.toEntity(dto);

        assertNotNull(zone);
        assertEquals("Zone1", zone.getNom());
    }
}
