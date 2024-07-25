package com.mrafp.idarati.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Data
@Table(name = "cout")
public class Cout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cout_id")
    private Long coutId;

    @Column(name = "valeur", nullable = false)
    private Double valeur;
}
