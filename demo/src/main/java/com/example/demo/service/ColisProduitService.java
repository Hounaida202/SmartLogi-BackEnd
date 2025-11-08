package com.example.demo.service;

import com.example.demo.dto.ColisProduitDTO;
import com.example.demo.entity.Colis;
import com.example.demo.entity.ColisProduit;
import com.example.demo.entity.Produit;
import com.example.demo.repository.ColisProduitRepository;
import com.example.demo.repository.ColisRepository;
import com.example.demo.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

@Service
public class ColisProduitService {

    private static final Logger logger = LoggerFactory.getLogger(ColisProduitService.class);

    @Autowired
    private ColisRepository colisRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private ColisProduitRepository colisProduitRepository;

    public ColisProduit assignerProduitAuColis(ColisProduitDTO dto) {

        logger.info("Assignation d’un produit au colis. DTO reçu : {}", dto);

        Colis colis = colisRepository.findById(dto.getIdColis())
                .orElseThrow(() -> {
                    logger.error("Colis introuvable pour ID={}", dto.getIdColis());
                    return new RuntimeException("Colis introuvable");
                });

        Produit produit = produitRepository.findById(dto.getIdProduit())
                .orElseThrow(() -> {
                    logger.error("Produit introuvable pour ID={}", dto.getIdProduit());
                    return new RuntimeException("Produit introuvable");
                });

        logger.debug("Colis trouvé : ID={}, Produit trouvé : ID={}", colis.getId(), produit.getId());

        ColisProduit cp = new ColisProduit();
        cp.setIdColis(colis);
        cp.setIdProduit(produit);
        cp.setQuantite(dto.getQuantite());
        cp.setPrix(dto.getPrix());
        cp.setDateAjout(Instant.now());

        ColisProduit saved = colisProduitRepository.save(cp);

        logger.info("Produit assigné avec succès au colis");

        return saved;
    }
}
