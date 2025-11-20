package com.example.demo.mapper;

import com.example.demo.dto.DestinataireDTO;
import com.example.demo.entity.Destinataire;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DestinataireMapperTest {

    private final DestinataireMapper mapper = DestinataireMapper.INSTANCE;

    @Test
    void testToDTO() {
        Destinataire entity = new Destinataire();
        entity.setNom("Nom");
        entity.setPrenom("Prenom");
        entity.setEmail("email@test.com");
        entity.setTelephone("0612345678");
        entity.setAdresse("Adresse");

        DestinataireDTO dto = mapper.toDTO(entity);

        assertEquals("Nom", dto.getNom());
        assertEquals("Prenom", dto.getPrenom());
        assertEquals("email@test.com", dto.getEmail());
        assertEquals("0612345678", dto.getTelephone());
        assertEquals("Adresse", dto.getAdresse());
    }

    @Test
    void testToEntity() {
        DestinataireDTO dto = new DestinataireDTO();
        dto.setNom("Nom");
        dto.setPrenom("Prenom");
        dto.setEmail("email@test.com");
        dto.setTelephone("0612345678");
        dto.setAdresse("Adresse");

        Destinataire entity = mapper.toEntity(dto);

        assertEquals("Nom", entity.getNom());
        assertEquals("Prenom", entity.getPrenom());
        assertEquals("email@test.com", entity.getEmail());
        assertEquals("0612345678", entity.getTelephone());
        assertEquals("Adresse", entity.getAdresse());
    }
}
