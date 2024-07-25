package com.mrafp.idarati.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Setter
@Getter
@Entity
@Data
@Table(name = "administration")
public class Administration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long administrationId;

    @Column(name = "nom_admin", nullable = false)
    private String nomAdmin;
}
