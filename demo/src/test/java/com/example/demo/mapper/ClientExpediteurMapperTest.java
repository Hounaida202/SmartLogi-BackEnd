package com.example.demo.mapper;

import com.example.demo.dto.ClientExpediteurDTO;
import com.example.demo.entity.ClientExpediteur;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientExpediteurMapperTest {

    private final ClientExpediteurMapper mapper = ClientExpediteurMapper.INSTANCE;

    @Test
    void testToDTO() {
        ClientExpediteur entity = new ClientExpediteur();
        entity.setNom("Nom");
        entity.setPrenom("Prenom");
        entity.setEmail("email@test.com");
        entity.setTelephone("0612345678");
        entity.setAdresse("Adresse");

        ClientExpediteurDTO dto = mapper.toDTO(entity);

        assertEquals("Nom", dto.getNom());
        assertEquals("Prenom", dto.getPrenom());
        assertEquals("email@test.com", dto.getEmail());
        assertEquals("0612345678", dto.getTelephone());
        assertEquals("Adresse", dto.getAdresse());
    }

    @Test
    void testToEntity() {
        ClientExpediteurDTO dto = new ClientExpediteurDTO();
        dto.setNom("Nom");
        dto.setPrenom("Prenom");
        dto.setEmail("email@test.com");
        dto.setTelephone("0612345678");
        dto.setAdresse("Adresse");

        ClientExpediteur entity = mapper.toEntity(dto);

        assertEquals("Nom", entity.getNom());
        assertEquals("Prenom", entity.getPrenom());
        assertEquals("email@test.com", entity.getEmail());
        assertEquals("0612345678", entity.getTelephone());
        assertEquals("Adresse", entity.getAdresse());
    }
}
