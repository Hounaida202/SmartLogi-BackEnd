package com.example.demo.mapper;

import com.example.demo.dto.ClientExpediteurDTO;
import com.example.demo.entity.ClientExpediteur;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientExpediteurMapper {


    ClientExpediteurMapper INSTANCE = Mappers.getMapper(ClientExpediteurMapper.class);

    ClientExpediteurDTO toDTO(ClientExpediteur clientExpediteur);


    ClientExpediteur toEntity(ClientExpediteurDTO clientExpediteurDTO);

}
