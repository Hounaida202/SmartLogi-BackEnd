package com.example.demo.service;

import com.example.demo.dto.ClientExpediteurDTO;
import com.example.demo.dto.DestinataireDTO;
import com.example.demo.entity.ClientExpediteur;
import com.example.demo.entity.Destinataire;
import com.example.demo.mapper.DestinataireMapper;
import com.example.demo.repository.DestinataireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DestinataireService {

    @Autowired
    private DestinataireRepository destinataireRepository;

    @Autowired
    private DestinataireMapper destinataireMapper;

    public DestinataireDTO saveDestinataire(DestinataireDTO destinataireDTO) {
        Destinataire destinataire = destinataireMapper.toEntity(destinataireDTO);
        destinataireRepository.save(destinataire);
        return destinataireMapper.toDTO(destinataire);
    }
}
