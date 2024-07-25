package com.mrafp.idarati.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Data
@Table(name = "antennes")
public class Antenne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long antenneId;

    @Column(name = "admin_depot", nullable = false)
    private String adminDepot;

    @Column(name = "admin_reception", nullable = false)
    private String adminReception;
}
