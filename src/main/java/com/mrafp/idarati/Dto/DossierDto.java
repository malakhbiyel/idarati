package com.mrafp.idarati.Dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DossierDto {

    private Long dossierId;
    private ProcedureDto procedure;
    private ConditionDto condition;
    private List<DocumentDto> documentsList;
}
