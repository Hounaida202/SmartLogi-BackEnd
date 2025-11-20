package com.example.demo.mapper;

import com.example.demo.dto.LivreurDTO;
import com.example.demo.entity.Livreur;
import com.example.demo.entity.Zone;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LivreurMapperTest {

    private final LivreurMapper mapper = LivreurMapper.INSTANCE;

    @Test
    void testToDTO() {
        Livreur entity = new Livreur();
        entity.setNom("Nom");
        entity.setPrenom("Prenom");
        entity.setTelephone("0612345678");
        entity.setVehicule("Moto");

        entity.setZoneAssignee(new Zone());
        entity.getZoneAssignee().setNom("Zone1");

        LivreurDTO dto = mapper.toDTO(entity);

        assertEquals("Nom", dto.getNom());
        assertEquals("Prenom", dto.getPrenom());
        assertEquals("0612345678", dto.getTelephone());
        assertEquals("Moto", dto.getVehicule());
        assertEquals("Zone1", dto.getZoneAssignee());
    }

    @Test
    void testToEntity() {
        LivreurDTO dto = new LivreurDTO();
        dto.setNom("Nom");
        dto.setPrenom("Prenom");
        dto.setTelephone("0612345678");
        dto.setVehicule("Moto");
        dto.setZoneAssignee("Zone1");

        Livreur entity = mapper.toEntity(dto);

        assertEquals("Nom", entity.getNom());
        assertEquals("Prenom", entity.getPrenom());
        assertEquals("0612345678", entity.getTelephone());
        assertEquals("Moto", entity.getVehicule());
        // zoneAssignee est ignoré -> zone reste null
        assertNull(entity.getZoneAssignee());
    }

    @Test
    void testMapZone() {
        Zone zone = new Zone();
        zone.setNom("ZoneX");
        assertEquals("ZoneX", mapper.map(zone));

        assertNull(mapper.map(null));
    }
}
