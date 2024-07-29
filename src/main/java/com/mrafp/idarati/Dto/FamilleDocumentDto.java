package com.mrafp.idarati.Dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FamilleDocumentDto {

    private Long familleId;
    private List<DocumentDto> documentsList;
}
