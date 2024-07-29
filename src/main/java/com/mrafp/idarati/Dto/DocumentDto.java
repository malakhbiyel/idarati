package com.mrafp.idarati.Dto;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDto {

    private Long documentId;
    private String code;
    private String titre;
    private String description;
}
