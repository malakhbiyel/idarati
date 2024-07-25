package com.mrafp.idarati.Repository;

import com.mrafp.idarati.Model.FamilleDocument;
import org.springframework.lang.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FamilleDocumentRepository extends JpaRepository<FamilleDocument, Long> {
    @NonNull
    Optional<FamilleDocument> findById(@NonNull Long id);
    @NonNull
    List<FamilleDocument> findAll();
}
