package com.example.demo.controller;

import com.example.demo.dto.DestinataireDTO;
import com.example.demo.dto.ZoneDTO;
import com.example.demo.service.DestinataireService;
import com.example.demo.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zones")
public class ZoneController {
    @Autowired
    private ZoneService zoneService;

    @PostMapping
    public ResponseEntity<ZoneDTO> createZone(@RequestBody ZoneDTO zoneDTO) {
        ZoneDTO savedZone = zoneService.saveZone(zoneDTO);
        return ResponseEntity.ok(savedZone);
    }
}
