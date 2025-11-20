package com.example.demo.DTO;

import com.example.demo.dto.LivreurDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LivreurDTOTest {

    @Test
    void testGettersAndSetters() {
        LivreurDTO dto = new LivreurDTO();

        dto.setNom("Ahmed");
        dto.setPrenom("Ali");
        dto.setTelephone("0601234567");
        dto.setVehicule("Moto");
        dto.setZoneAssignee("Zone1");

        assertEquals("Ahmed", dto.getNom());
        assertEquals("Ali", dto.getPrenom());
        assertEquals("0601234567", dto.getTelephone());
        assertEquals("Moto", dto.getVehicule());
        assertEquals("Zone1", dto.getZoneAssignee());
    }
}
