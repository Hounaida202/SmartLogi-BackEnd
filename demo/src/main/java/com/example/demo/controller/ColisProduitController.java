package com.example.demo.controller;

import com.example.demo.dto.ColisProduitDTO;
import com.example.demo.entity.ColisProduit;
import com.example.demo.service.ColisProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/colis-produit")
public class ColisProduitController {

    @Autowired
    private ColisProduitService colisProduitService;

    @PostMapping("/assign")
    public ColisProduit assigner(@RequestBody ColisProduitDTO dto) {
        return colisProduitService.assignerProduitAuColis(dto);
    }
}

