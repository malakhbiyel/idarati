package com.mrafp.idarati.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@Entity
@Data
@Table(name = "famille_document")
public class FamilleDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "famille_id")
    private Long familleId;

    @OneToMany(mappedBy = "familleDocument", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Document> documentsList;

}
