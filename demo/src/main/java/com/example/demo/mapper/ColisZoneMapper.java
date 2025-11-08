package com.example.demo.mapper;

import com.example.demo.dto.ColisAvecZoneDTO;
import com.example.demo.entity.Colis;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses =
                ZoneColisMapper.class

)
public interface ColisZoneMapper {

    @Mapping(target = "idLivreur", ignore = true)
    @Mapping(target = "idDestinataire", ignore = true)
    @Mapping(target = "idClientExpediteur", ignore = true)
    ColisAvecZoneDTO toDTO(Colis colis);

    @Mapping(target = "idLivreur", ignore = true)
    @Mapping(target = "idDestinataire", ignore = true)
    @Mapping(target = "idClientExpediteur", ignore = true)
    Colis toEntity(ColisAvecZoneDTO colisAvecZoneDTO);

}
