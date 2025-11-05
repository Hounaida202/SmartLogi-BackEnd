package com.example.demo.controller;

import com.example.demo.dto.ClientExpediteurDTO;
import com.example.demo.dto.DestinataireDTO;
import com.example.demo.service.ClientExpediteurService;
import com.example.demo.service.DestinataireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/destinataires")
public class DestinataireController {

    @Autowired
    private DestinataireService destinataireService;

    @PostMapping
    public ResponseEntity<DestinataireDTO> createDestinataire(@RequestBody DestinataireDTO destinataireDTO) {
        DestinataireDTO savedDestinataire = destinataireService.saveDestinataire(destinataireDTO);
        return ResponseEntity.ok(savedDestinataire);
    }
}
