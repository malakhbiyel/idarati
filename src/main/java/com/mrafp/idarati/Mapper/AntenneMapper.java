package com.mrafp.idarati.Mapper;

import com.mrafp.idarati.Dto.AntenneDto;
import com.mrafp.idarati.Model.Antenne;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AntenneMapper {

    AntenneDto toDto(Antenne antenne);
    Antenne toEntity(AntenneDto antenneDto);
}
