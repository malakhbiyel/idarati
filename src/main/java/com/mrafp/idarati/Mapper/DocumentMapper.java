package com.mrafp.idarati.Mapper;


import com.mrafp.idarati.Dto.DocumentDto;
import com.mrafp.idarati.Model.Document;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

    DocumentDto toDto(Document document);
    Document toEntity(DocumentDto dto);
}
