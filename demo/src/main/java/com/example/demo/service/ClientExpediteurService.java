package com.example.demo.service;

import com.example.demo.dto.ClientExpediteurDTO;
import com.example.demo.entity.ClientExpediteur;
import com.example.demo.mapper.ClientExpediteurMapper;
import com.example.demo.repository.ClientExpediteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientExpediteurService {
    @Autowired
    private ClientExpediteurRepository clientExpediteurRepository;
    @Autowired
    private ClientExpediteurMapper clientExpediteurMapper;

    public ClientExpediteurDTO saveUser(ClientExpediteurDTO clientExpediteurDTO) {
        ClientExpediteur clientExpediteur = clientExpediteurMapper.toEntity(clientExpediteurDTO);
        clientExpediteurRepository.save(clientExpediteur);
        return clientExpediteurMapper.toDTO(clientExpediteur);
    }

}
