package com.example.demo.controller;

import com.example.demo.dto.ClientExpediteurDTO;
import com.example.demo.service.ClientExpediteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientExpediteurs")

public class ClientExpediteurController {



    @Autowired
    private ClientExpediteurService clientExpediteurService;

    @PostMapping
    public ResponseEntity<ClientExpediteurDTO> createUser(@RequestBody ClientExpediteurDTO clientExpediteurDTO) {
        ClientExpediteurDTO savedUser = clientExpediteurService.saveUser(clientExpediteurDTO);
        return ResponseEntity.ok(savedUser);
    }


    @GetMapping("/search")
    public List<ClientExpediteurDTO> chercherClientsParNom(@RequestParam String nom) {
        return clientExpediteurService.getClientsParNom(nom);
    }



}
