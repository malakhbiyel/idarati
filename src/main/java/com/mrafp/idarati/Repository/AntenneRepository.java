package com.mrafp.idarati.Repository;

import com.mrafp.idarati.Model.Antenne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;


public interface AntenneRepository extends JpaRepository<Antenne, Long> {

    @NonNull
    Optional<Antenne> findById(@NonNull Long id);
    @NonNull
    List<Antenne> findAll();
    Optional<Antenne> findByAdminDepot(String adminDepot);
    Optional<Antenne> findByAdminReception(String adminReception);
}
