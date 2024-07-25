package com.mrafp.idarati.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Data
@Table(name = "delai")
public class Delai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delai_id")
    private Long delaiId;

    @Column(name = "duree", nullable = false)
    private Integer duree;
}
