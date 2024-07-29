package com.mrafp.idarati.Mapper;

import com.mrafp.idarati.Dto.AdministrationDto;
import com.mrafp.idarati.Model.Administration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdministrationMapper {

    AdministrationDto toDto(Administration administration);

    Administration toEntity(AdministrationDto administrationDto);
}
