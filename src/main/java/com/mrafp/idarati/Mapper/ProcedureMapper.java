package com.mrafp.idarati.Mapper;

import com.mrafp.idarati.Dto.ProcedureDto;
import com.mrafp.idarati.Model.Procedure;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProcedureMapper {

    ProcedureDto toDto(Procedure procedure);

    Procedure toEntity(ProcedureDto procedureDto);
}
