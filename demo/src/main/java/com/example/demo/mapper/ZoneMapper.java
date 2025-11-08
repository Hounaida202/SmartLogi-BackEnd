package com.example.demo.mapper;

import com.example.demo.dto.DestinataireDTO;
import com.example.demo.dto.ZoneDTO;
import com.example.demo.entity.Destinataire;
import com.example.demo.entity.Zone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")

public interface ZoneMapper {
    ZoneMapper INSTANCE = Mappers.getMapper(ZoneMapper.class);


    ZoneDTO toDTO(Zone zone);

/*
    @Mapping(target = "id", ignore = true)
*/
    Zone toEntity(ZoneDTO zoneDTO);

}
