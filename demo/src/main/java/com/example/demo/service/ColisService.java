package com.example.demo.service;

import com.example.demo.dto.ColisAvecZoneDTO;
import com.example.demo.dto.ColisDTO;
import com.example.demo.dto.UpdateStautColisDTO;
import com.example.demo.entity.ClientExpediteur;
import com.example.demo.entity.Colis;
import com.example.demo.entity.Destinataire;
import com.example.demo.entity.Livreur;
import com.example.demo.mapper.ColisMapper;
import com.example.demo.mapper.ColisZoneMapper;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ColisService {

    private static final Logger logger = LoggerFactory.getLogger(ColisService.class);

    @Autowired
    private ColisRepository colisRepository;

    @Autowired
    private DestinataireRepository destinataireRepository;

    @Autowired
    private ClientExpediteurRepository clientExpediteurRepository;

    @Autowired
    private ColisZoneMapper coliszoneMapper;

    @Autowired
    private ColisMapper colisMapper;

    @Autowired
    private LivreurRepository livreurRepository;



    public ColisAvecZoneDTO creerColisAvecZone(ColisAvecZoneDTO colisAvecZoneDTO) {
        logger.info("Création du colis avec zone: {}", colisAvecZoneDTO);

        Colis colis = coliszoneMapper.toEntity(colisAvecZoneDTO);
        ClientExpediteur client = clientExpediteurRepository
                .findById(colisAvecZoneDTO.getIdClientExpediteur())
                .orElseThrow(() -> new RuntimeException("Client introuvable"));

        colis.setIdClientExpediteur(client);
        Destinataire destinataire = destinataireRepository
                .findById(colisAvecZoneDTO.getIdDestinataire())
                .orElseThrow(() -> new RuntimeException("Destinataire introuvable")
                );

        colis.setIdDestinataire(destinataire);

        colis.setIdLivreur(null);

        colisRepository.save(colis);

        logger.info("Colis créé avec succès ID={}", colis.getId());

        return colisAvecZoneDTO;
    }





    public ColisDTO saveColis(ColisDTO colisDTO) {
        logger.info("Enregistrement du colis: {}", colisDTO);
        Colis colis = colisMapper.toEntity(colisDTO);
        colisRepository.save(colis);
        logger.info("Colis enregistré ID={}", colis.getId());
        return colisMapper.toDTO(colis);
    }





    public Page<ColisDTO> getColisByClientAndStatut(Long idClientExpediteur, Pageable pageable) {
        logger.info("Recherche des colis pour client={}, statuts=crée,livré", idClientExpediteur);
        List<String> statuts = List.of("crée", "livré");

        Page<Colis> pageColis = colisRepository.findByIdClientExpediteur_IdAndStatutIn(
                idClientExpediteur, statuts, pageable
        );
        logger.debug("Nombre de colis trouvés: {}", pageColis.getTotalElements());
        return pageColis.map(colisMapper::toDTO);
    }





    public Optional<Colis> trouverParId(Long id) {
        logger.info("Recherche du colis ID={}", id);
        return colisRepository.findById(id);
    }




    public List<String> getStatutColisParDestinataire(Long destinataireId) {
        logger.info("Récupération des statuts pour destinataire ID={}", destinataireId);

        List<Colis> colisList = colisRepository.findByIdDestinataire_Id(destinataireId);

        logger.debug("Nombre de colis trouvés: {}", colisList.size());
        return colisList.stream().map(Colis::getStatut).toList();
    }




    public List<ColisAvecZoneDTO> getColisAvecZoneParLivreur(Long idLivreur) {
        logger.info("Récupération des colis pour livreur ID={}", idLivreur);
        List<Colis> colisList = colisRepository.findByIdLivreur_Id(idLivreur);
        logger.debug("Colis trouvés: {}", colisList.size());
        return colisList.stream().map(coliszoneMapper::toDTO).toList();
    }




    public ColisDTO updateStautColis(Long id, UpdateStautColisDTO dto) {
        logger.info("Mise à jour du statut du colis ID={}, nouveau statut={}", id, dto.getStatut());

        Colis colis = colisRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Colis non trouvé ID={}", id);
                    return new RuntimeException("id non trouvé " + id);
                });

        colisMapper.updateStatutFromDTO(dto, colis);
        colisRepository.save(colis);

        logger.info("Statut du colis mis à jour avec succès ID={}", colis.getId());
        return colisMapper.toDTO(colis);
    }





    public List<ColisAvecZoneDTO> getColisSansLivreur() {
        logger.info("Récupération des colis sans livreur");
        List<Colis> colisList = colisRepository.findByIdLivreurIsNull();
        logger.debug("Colis sans livreur: {}", colisList.size());
        return colisList.stream().map(coliszoneMapper::toDTO).toList();
    }




    public ColisAvecZoneDTO assignerLivreurAuColis(Long idColis, Long idLivreur) {

        logger.info("Assignation du livreur ID={} au colis ID={}", idLivreur, idColis);

        Colis colis = colisRepository.findById(idColis)
                .orElseThrow(() -> {
                    logger.error("Colis non trouvé ID={}", idColis);
                    return new RuntimeException("Colis non trouvé");
                });

        Livreur livreur = livreurRepository.findById(idLivreur)
                .orElseThrow(() -> {
                    logger.error("Livreur non trouvé ID={}", idLivreur);
                    return new RuntimeException("Livreur non trouvé");
                });

        colis.setIdLivreur(livreur);

        Colis colisSauvegarde = colisRepository.save(colis);

        logger.info("Livreur ID={} assigné au colis ID={}", idLivreur, idColis);

        return coliszoneMapper.toDTO(colisSauvegarde);
    }





    public Page<ColisAvecZoneDTO> getColisFiltres(
            List<String> statuts,
            Long idZone,
            String ville,
            List<String> priorites,
            Pageable pageable) {

        logger.info("Filtrage des colis: statuts={}, zone={}, ville={}, priorités={}",
                statuts, idZone, ville, priorites);

        Page<Colis> pageColis = colisRepository
                .findByStatutInAndIdZone_IdAndVilleDestinationContainingIgnoreCaseAndPrioriteIn(
                        statuts,
                        idZone,
                        ville != null ? ville : "",
                        priorites,
                        pageable
                );

        logger.debug("Nombre de colis filtrés: {}", pageColis.getTotalElements());
        return pageColis.map(coliszoneMapper::toDTO);
    }





    public List<ColisAvecZoneDTO> getColisParNomZone(String nomZone) {
        logger.info("Récupération des colis pour la zone {}", nomZone);
        List<Colis> colisList = colisRepository.findByIdZone_NomIgnoreCase(nomZone);
        return colisList.stream().map(coliszoneMapper::toDTO).toList();
    }





    public List<ColisAvecZoneDTO> getColisParVille(String ville) {
        logger.info("Récupération des colis pour la ville {}", ville);
        List<Colis> colisList = colisRepository.findByVilleDestinationIgnoreCase(ville);
        return colisList.stream().map(coliszoneMapper::toDTO).toList();
    }






    public long getNombreTotalColisParLivreur(Long idLivreur) {
        logger.info("Calcul du nombre total de colis pour livreur ID={}", idLivreur);
        return colisRepository.countByIdLivreur_Id(idLivreur);
    }






    public BigDecimal getPoidsTotalParLivreur(Long idLivreur) {
        logger.info("Calcul du poids total pour livreur ID={}", idLivreur);
        return colisRepository.getPoidsTotalParLivreur(idLivreur);
    }
}

