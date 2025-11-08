package com.example.demo.mapper;

import com.example.demo.dto.LivreurDTO;
import com.example.demo.entity.Livreur;
import com.example.demo.entity.Zone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LivreurMapper {

    LivreurMapper INSTANCE = Mappers.getMapper(LivreurMapper.class);

    @Mapping(target = "zoneAssignee", ignore = true)
    Livreur toEntity(LivreurDTO livreurDTO);

    @Mapping(target = "zoneAssignee", source = "zoneAssignee")
    LivreurDTO toDTO(Livreur livreur);

    default String map(Zone zone) {
        return zone != null ? zone.getNom() : null;
    }
}
