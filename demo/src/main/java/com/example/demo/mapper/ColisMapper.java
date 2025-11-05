package com.example.demo.mapper;

import com.example.demo.dto.ColisDTO;
import com.example.demo.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ColisMapper {
    ColisMapper INSTANCE = Mappers.getMapper(ColisMapper.class);

    // Entity -> DTO

    ColisDTO toDTO(Colis colis);


    Colis toEntity(ColisDTO colisDTO);




}
