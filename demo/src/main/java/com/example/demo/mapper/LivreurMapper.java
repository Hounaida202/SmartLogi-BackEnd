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


    Livreur toEntity(LivreurDTO livreurDTO);

    LivreurDTO toDTO(Livreur livreur);

}
