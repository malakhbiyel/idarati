package com.mrafp.idarati.Mapper;

import com.mrafp.idarati.Dto.DelaiDto;
import com.mrafp.idarati.Model.Delai;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DelaiMapper {

    DelaiDto toDto(Delai delai);
    Delai toEntity(DelaiDto delaiDto);
}
