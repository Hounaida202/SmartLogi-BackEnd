package com.example.demo.service;

import com.example.demo.dto.DestinataireDTO;
import com.example.demo.entity.Destinataire;
import com.example.demo.mapper.DestinataireMapper;
import com.example.demo.repository.DestinataireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DestinataireService {

    private static final Logger logger = LoggerFactory.getLogger(DestinataireService.class);

    @Autowired
    private DestinataireRepository destinataireRepository;

    @Autowired
    private DestinataireMapper destinataireMapper;

    public DestinataireDTO saveDestinataire(DestinataireDTO destinataireDTO) {
        logger.info("Création d’un nouveau destinataire : {}", destinataireDTO);
        Destinataire destinataire = destinataireMapper.toEntity(destinataireDTO);
        destinataireRepository.save(destinataire);
        logger.info("Destinataire enregistré avec succès, ID={}", destinataire.getId());
        return destinataireMapper.toDTO(destinataire);
    }
}
