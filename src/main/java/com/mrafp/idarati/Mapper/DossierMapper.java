package com.mrafp.idarati.Mapper;

import com.mrafp.idarati.Dto.DossierDto;
import com.mrafp.idarati.Model.Dossier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {ProcedureMapper.class, ConditionMapper.class, DocumentMapper.class})
public interface DossierMapper {

    @Mappings({
            @Mapping(source = "procedure", target = "procedure"),
            @Mapping(source = "condition", target = "condition"),
            @Mapping(source = "documentsList", target = "documentsList")
    })
    DossierDto toDto(Dossier dossier);

    @Mappings({
            @Mapping(source = "procedure", target = "procedure"),
            @Mapping(source = "condition", target = "condition"),
            @Mapping(source = "documentsList", target = "documentsList")
    })
    Dossier toEntity(DossierDto dossierDto);

}
