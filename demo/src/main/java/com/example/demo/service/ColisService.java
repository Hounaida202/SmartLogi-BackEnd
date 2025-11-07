package com.example.demo.service;

import com.example.demo.dto.ColisDTO;
import com.example.demo.dto.UpdateColisDTO;
import com.example.demo.dto.UpdateStautColisDTO;
import com.example.demo.entity.*;
import com.example.demo.mapper.ColisMapper;

import com.example.demo.mapper.ZoneMapper;
import com.example.demo.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColisService {
    @Autowired
    private ColisRepository colisRepository;

    @Autowired
    private ColisMapper colisMapper;

    @Autowired
    private ZoneMapper zoneMapper;



    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private DestinataireRepository destinataireRepository;

    @Autowired
    private ClientExpediteurRepository clientExpediteurRepository;





    /*public ColisRequestDTO creerColisAvecZone(ColisRequestDTO colisRequestDTO){
        //1 convertir zonedto e zone
           Zone zone = zoneMapper.toEntity(colisRequestDTO.getZone());
        //2 save cet zone
        zoneRepository.save(zone);

//        convertir le colis dto en colis
                Colis colis = colisRequestMapper.toEntity(colisRequestDTO);
        //3 set cette zone dans le  colis
             colis.setIdZone(zone);

//        4 save le colis
             colisRepository.save(colis);


                return  colisRequestMapper.toDTO(colis);
    }*/














    public ColisDTO saveColis(ColisDTO colisDTO) {
        Colis colis = colisMapper.toEntity(colisDTO);
        colisRepository.save(colis);
        return colisMapper.toDTO(colis);
    }

    public Page<ColisDTO> getColisByClientAndStatut(Long idClientExpediteur, Pageable pageable) {

        List<String> statuts = List.of("crée", "livré");
        Page<Colis> pageColis = colisRepository.findByIdClientExpediteur_IdAndStatutIn(
                idClientExpediteur,
                statuts,
                pageable
        );
        return pageColis.map(colisMapper::toDTO);
    }

    public Optional<Colis> trouverParId(Long id){
        return colisRepository.findById(id);
    }

    /*public Page<ColisDTO> getColisByIdLivreur(Long idLivreur, Pageable pageable) {

        Page<Colis> pageColis = colisRepository.findByIdLivreur(
                idLivreur,
                pageable
        );
        return pageColis.map(colisMapper::toDTO);
    }*/

    public Page<ColisDTO> getColisByIdLivreur(Long idLivreur, Pageable pageable) {
        Page<Colis> pageColis = colisRepository.findByIdLivreur_Id(idLivreur, pageable);
        return pageColis.map(colisMapper::toDTO);
    }




    @Autowired
    private LivreurRepository livreurRepository;

    public void updateIdColis(Long id, UpdateColisDTO colisDTO) {
        Colis colis = trouverParId(id)
                .orElseThrow(() -> new RuntimeException("Colis non trouvé avec id : " + id));

        Livreur livreur = livreurRepository.findById(colisDTO.getIdLivreur())
                .orElseThrow(() -> new RuntimeException("Livreur non trouvé avec id : " + colisDTO.getIdLivreur()));

        colis.setIdLivreur(livreur);
        colisRepository.save(colis);
    }


    public ColisDTO updateStautColis(Long id , UpdateStautColisDTO dto){
        Colis colis = colisRepository.findById(id)
                .orElseThrow(()->new RuntimeException("id non trouvé"+ id));

        colisMapper.updateStatutFromDTO(dto , colis);
        colisRepository.save(colis);
        return colisMapper.toDTO(colis);
    }


/*
    le but c est de recuperer les colis avec idlivreur null , ms sans pagination cette fois ci
*/



    public List<ColisDTO> getColisEnAttent(){

        List<Colis> desColis = colisRepository.findAll();
        List<ColisDTO> colisDtoList = desColis.stream()
                .map(colisMapper::toDTO)
                .toList();

        return colisDtoList;
    }


    public void creerColisProduitZone(Colis colis){
        colisRepository.save(colis);
    }








    public void updateColis(){


    }

















}
