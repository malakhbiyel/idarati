package com.mrafp.idarati.Mapper;

import com.mrafp.idarati.Dto.FamilleDocumentDto;
import com.mrafp.idarati.Model.FamilleDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FamilleDocumentMapper {

    FamilleDocumentDto toDto(FamilleDocument familleDocument);
    FamilleDocument toEntity(FamilleDocumentDto familleDocumentDto);
}

