package com.mrafp.idarati.Mapper;

import com.mrafp.idarati.Dto.CoutDto;
import com.mrafp.idarati.Model.Cout;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoutMapper {

    CoutDto toDto(Cout cout);
    Cout toEntity(CoutDto coutDto);
}
