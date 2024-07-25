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
@Table(name = "procedure")
public class Procedure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long procedureId;

    @Column(name = "titre", nullable = false)
    private String titre;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "admin_source_id", nullable = false)
    private Administration adminSource;

    @ManyToOne
    @JoinColumn(name = "antenne_id", nullable = false)
    private Antenne antenne;

    @ManyToOne
    @JoinColumn(name = "delai_id", nullable = false)
    private Delai delai;

    @ManyToOne
    @JoinColumn(name = "cout_id", nullable = false)
    private Cout cout;

    @OneToMany(mappedBy = "procedure")
    private List<Dossier> dossiers;

}
