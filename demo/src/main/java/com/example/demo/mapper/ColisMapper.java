package com.example.demo.mapper;

import com.example.demo.dto.ColisDTO;
import com.example.demo.dto.UpdateStautColisDTO;
import com.example.demo.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ColisMapper {
    ColisMapper INSTANCE = Mappers.getMapper(ColisMapper.class);

    @Mapping(source = "idLivreur.id", target = "idLivreur")
    @Mapping(source = "idClientExpediteur.id", target = "idClientExpediteur")
    @Mapping(source = "idDestinataire.id", target = "idDestinataire")
    @Mapping(source = "idZone.id", target = "idZone")
    @Mapping(source = "idZone.nom", target = "nomZone")

    ColisDTO toDTO(Colis colis);

    @Mapping(source = "idLivreur", target = "idLivreur", qualifiedByName = "longToLivreur")
    @Mapping(source = "idClientExpediteur", target = "idClientExpediteur", qualifiedByName = "longToClient")
    @Mapping(source = "idDestinataire", target = "idDestinataire", qualifiedByName = "longToDestinataire")
    @Mapping(source = "idZone", target = "idZone", qualifiedByName = "longToZone")


    Colis toEntity(ColisDTO colisDTO);

    @Named("longToLivreur")
    default Livreur longToLivreur(Long id) {
        if (id == null) return null;
        Livreur livreur = new Livreur();
        livreur.setId(id);
        return livreur;
    }

    @Named("longToClient")
    default ClientExpediteur longToClient(Long id) {
        if (id == null) return null;
        ClientExpediteur c = new ClientExpediteur();
        c.setId(id);
        return c;
    }

    @Named("longToDestinataire")
    default Destinataire longToDestinataire(Long id) {
        if (id == null) return null;
        Destinataire d = new Destinataire();
        d.setId(id);
        return d;
    }

    @Named("longToZone")
    default Zone longToZone(Long id) {
        if (id == null) return null;
        Zone z = new Zone();
        z.setId(id);
        return z;
    }



    void updateStatutFromDTO(UpdateStautColisDTO dto , @MappingTarget Colis entity);


}
