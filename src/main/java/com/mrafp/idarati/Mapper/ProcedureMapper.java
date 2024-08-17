package com.mrafp.idarati.Mapper;

import com.mrafp.idarati.Dto.ProcedureDto;
import com.mrafp.idarati.Model.Procedure;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {AdministrationMapper.class, AntenneMapper.class, DelaiMapper.class, CoutMapper.class})
public interface ProcedureMapper {
    @Mappings({
            @Mapping(source = "administration", target = "administration"),
            @Mapping(source = "antenne", target = "antenne"),
            @Mapping(source = "delai", target = "delai"),
            @Mapping(source = "cout", target = "cout")
    })
    ProcedureDto toDto(Procedure procedure);

    @Mappings({
            @Mapping(source = "administration", target = "administration"),
            @Mapping(source = "antenne", target = "antenne"),
            @Mapping(source = "delai", target = "delai"),
            @Mapping(source = "cout", target = "cout")
    })
    Procedure toEntity(ProcedureDto procedureDto);
}
