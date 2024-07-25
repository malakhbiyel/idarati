package com.mrafp.idarati.Repository;

import com.mrafp.idarati.Model.Dossier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface DossierRepository extends JpaRepository<Dossier, Long> {

    @NonNull
    Optional<Dossier> findById(@NonNull Long id);
    @NonNull
    List<Dossier> findAll();
    List<Dossier> findByProcedure_ProcedureId(Long procedureId);
    List<Dossier> findByCondition_ConditionId(Long conditionId);
    List<Dossier> findByDocumentsList_DocumentId(Long documentId);
}
