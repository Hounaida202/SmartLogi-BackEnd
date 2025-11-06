package com.example.demo.mapper;

import com.example.demo.dto.ProduitDTO;
import com.example.demo.entity.Produit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProduitMapper {

    @Mapping(target = "id", ignore = true)
    Produit toEntity(ProduitDTO dto);
}
