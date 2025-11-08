package com.example.demo.controller;

import com.example.demo.dto.ProduitDTO;
import com.example.demo.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produits")
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    @PostMapping("/create")
    public ProduitDTO createProduit(@RequestBody ProduitDTO dto) {
        return produitService.creerProduit(dto);
    }
}
