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
    private AdministrationDto administration;
    private AntenneDto antenne;
    private DelaiDto delai;
    private CoutDto cout;
}
