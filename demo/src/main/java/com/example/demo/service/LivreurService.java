package com.example.demo.service;

import com.example.demo.dto.ColisDTO;
import com.example.demo.dto.LivreurDTO;
import com.example.demo.entity.Colis;
import com.example.demo.entity.Livreur;
import com.example.demo.entity.Zone;
import com.example.demo.mapper.ColisMapper;
import com.example.demo.mapper.LivreurMapper;
import com.example.demo.repository.ColisRepository;
import com.example.demo.repository.LivreurRepository;
import com.example.demo.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivreurService {

    @Autowired
    private LivreurRepository livreurRepository;
    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired()
    private LivreurMapper livreurMapper;

    public Optional<Zone> findByNom(String nom){
        return zoneRepository.findByNom(nom);
    }
    public LivreurDTO saveLivreur(LivreurDTO livreurDTO){

        Livreur livreur = livreurMapper.toEntity(livreurDTO);

        Zone zone = findByNom(livreurDTO.getZoneAssignee())
                .orElseThrow(()-> new RuntimeException("false" + livreurDTO.getZoneAssignee()));

        livreur.setZoneAssignee(zone);

        livreurRepository.save(livreur);

        return livreurMapper.toDTO(livreur);


    }


}
