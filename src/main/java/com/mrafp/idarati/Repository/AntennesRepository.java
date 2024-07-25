package com.mrafp.idarati.Repository;

import com.mrafp.idarati.Model.Antenne;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AntennesRepository extends JpaRepository<Antenne, Long> {

    Antenne findByAdminDepot(String adminDepot);
    Antenne findByAdminReception(String adminReception);
}
