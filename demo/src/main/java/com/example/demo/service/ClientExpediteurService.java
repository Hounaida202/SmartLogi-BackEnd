package com.example.demo.service;

import com.example.demo.dto.ClientExpediteurDTO;
import com.example.demo.entity.ClientExpediteur;
import com.example.demo.mapper.ClientExpediteurMapper;
import com.example.demo.repository.ClientExpediteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class ClientExpediteurService {

    private static final Logger logger = LoggerFactory.getLogger(ClientExpediteurService.class);

    @Autowired
    private ClientExpediteurRepository clientExpediteurRepository;

    @Autowired
    private ClientExpediteurMapper clientExpediteurMapper;


    public ClientExpediteurDTO saveUser(ClientExpediteurDTO clientExpediteurDTO) {

        logger.info("Création d'un nouveau client expéditeur : {}", clientExpediteurDTO);

        ClientExpediteur clientExpediteur = clientExpediteurMapper.toEntity(clientExpediteurDTO);
        clientExpediteurRepository.save(clientExpediteur);

        logger.info("Client expéditeur enregistré avec succès, ID={}", clientExpediteur.getId());

        return clientExpediteurMapper.toDTO(clientExpediteur);
    }


    public List<ClientExpediteurDTO> getClientsParNom(String nom) {

        logger.info("Recherche des clients expéditeurs avec le nom : {}", nom);

        List<ClientExpediteur> clients = clientExpediteurRepository.findByNomIgnoreCase(nom);

        logger.debug("Nombre de clients trouvés : {}", clients.size());

        return clients.stream()
                .map(clientExpediteurMapper::toDTO)
                .toList();
    }

}
