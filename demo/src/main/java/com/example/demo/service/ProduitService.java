package com.example.demo.service;

import com.example.demo.dto.ProduitDTO;
import com.example.demo.entity.Produit;
import com.example.demo.mapper.ProduitMapper;
import com.example.demo.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private ProduitMapper produitMapper;

    public ProduitDTO creerProduit(ProduitDTO dto) {
        Produit produit = produitMapper.toEntity(dto);
//        produit.setId(System.currentTimeMillis());
        Produit saved = produitRepository.save(produit);

        return produitMapper.toDTO(saved);
    }

}
