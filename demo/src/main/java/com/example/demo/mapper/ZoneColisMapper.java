package com.example.demo.mapper;

import com.example.demo.dto.ZoneAvecColisDTO;
import com.example.demo.dto.ZoneDTO;
import com.example.demo.entity.Zone;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface ZoneColisMapper {

    ZoneAvecColisDTO toDTO(Zone zone);

/*
    @Mapping(target = "id", ignore = true)
*/
    Zone toEntity(ZoneAvecColisDTO zoneDTO);
}
