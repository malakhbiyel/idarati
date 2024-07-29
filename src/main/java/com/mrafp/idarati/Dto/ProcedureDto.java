package com.mrafp.idarati.Dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProcedureDto {

    private Long procedureId;
    private String titre;
    private String description;
    private Long adminSourceId;
    private Long antenneId;
    private Long delaiId;
    private Long coutId;
}
