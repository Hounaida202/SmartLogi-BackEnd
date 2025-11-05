package com.example.demo.service;

import com.example.demo.dto.DestinataireDTO;
import com.example.demo.dto.ZoneDTO;
import com.example.demo.entity.Destinataire;
import com.example.demo.entity.Zone;
import com.example.demo.mapper.DestinataireMapper;
import com.example.demo.mapper.ZoneMapper;
import com.example.demo.repository.DestinataireRepository;
import com.example.demo.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZoneService {
    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private ZoneMapper zoneMapper;

    public ZoneDTO saveZone(ZoneDTO zoneDTO) {
        Zone zone = zoneMapper.toEntity(zoneDTO);
        zoneRepository.save(zone);
        return zoneMapper.toDTO(zone);
    }
}
