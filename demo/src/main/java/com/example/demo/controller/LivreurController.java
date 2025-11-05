package com.example.demo.controller;

import com.example.demo.dto.LivreurDTO;
import com.example.demo.service.LivreurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livreur")

public class LivreurController {
    @Autowired
    private LivreurService livreurService;

    @PostMapping
    public ResponseEntity<LivreurDTO> createLivreur(@RequestBody LivreurDTO livreurDTO) {
        LivreurDTO savedLivreur = livreurService.saveLivreur(livreurDTO);
        return ResponseEntity.ok(savedLivreur);
    }
}
