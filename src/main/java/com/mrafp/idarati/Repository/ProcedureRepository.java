package com.mrafp.idarati.Repository;

import com.mrafp.idarati.Model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface ProcedureRepository extends JpaRepository<Procedure, Long> {
    @NonNull
    Optional<Procedure> findById(@NonNull Long id);
    @NonNull
    List<Procedure> findAll();
    Optional<Procedure> findByTitre(String titre);
    List<Procedure> findByAdminSource_AdministrationId(@NonNull Long adminSourceId);
}
