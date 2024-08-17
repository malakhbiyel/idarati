package com.mrafp.idarati.Repository;

import com.mrafp.idarati.Model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    @NonNull
    Optional<Document> findById(@NonNull Long id);
    @NonNull
    List<Document> findAll();
    @NonNull
    List<Document> findByTitre(@NonNull String titre);
    List<Document> findByCode(@NonNull String code);
}
