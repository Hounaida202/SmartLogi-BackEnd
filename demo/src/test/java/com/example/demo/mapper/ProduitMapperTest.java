package com.example.demo.mapper;

import com.example.demo.dto.ProduitDTO;
import com.example.demo.entity.Produit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProduitMapperTest {

    private final ProduitMapper mapper = new ProduitMapperImpl();

    @Test
    void testToDTO() {
        Produit produit = new Produit();
        produit.setNom("Produit1");
        produit.setPrix(null);

        ProduitDTO dto = mapper.toDTO(produit);

        assertNotNull(dto);
        assertEquals("Produit1", dto.getNom());
        assertNull(dto.getPrix());
    }

    @Test
    void testToEntity() {
        ProduitDTO dto = new ProduitDTO();
        dto.setNom("Produit1");
        dto.setPrix(null);

        Produit produit = mapper.toEntity(dto);

        assertNotNull(produit);
        assertEquals("Produit1", produit.getNom());
        assertNull(produit.getPrix());
    }
}
