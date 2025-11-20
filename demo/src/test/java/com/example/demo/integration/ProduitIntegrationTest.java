package com.example.demo.integration;

import com.example.demo.dto.ProduitDTO;
import com.example.demo.entity.Produit;
import com.example.demo.repository.ProduitRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class ProduitIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProduitRepository produitRepository;

    @BeforeEach
    void setUp() {
        produitRepository.deleteAll();
    }

    @Test
    void testCreateProduitIntegration() throws Exception {
        ProduitDTO dto = new ProduitDTO();
        dto.setNom("ProduitTest");
        dto.setPrix(BigDecimal.valueOf(25.5));
        dto.setPoids(BigDecimal.valueOf(1.0));
        dto.setCategorie("Autre");


        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/produits/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("ProduitTest"))
                .andExpect(jsonPath("$.prix").value(25.5));

        List<Produit> resultat = produitRepository.findAll();
        assertThat(resultat).hasSize(1);

        Produit saved = resultat.get(0);
        assertThat(saved.getNom()).isEqualTo("ProduitTest");
        assertThat(saved.getPrix()).isEqualByComparingTo(BigDecimal.valueOf(25.5));
    }
}
