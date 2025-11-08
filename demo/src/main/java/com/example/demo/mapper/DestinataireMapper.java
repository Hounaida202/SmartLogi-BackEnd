package com.example.demo.mapper;

import com.example.demo.dto.ClientExpediteurDTO;
import com.example.demo.dto.DestinataireDTO;
import com.example.demo.entity.ClientExpediteur;
import com.example.demo.entity.Destinataire;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DestinataireMapper {
    DestinataireMapper INSTANCE = Mappers.getMapper(DestinataireMapper.class);


    DestinataireDTO toDTO(Destinataire destinataire);

    Destinataire toEntity(DestinataireDTO destinataireDTO);

}
