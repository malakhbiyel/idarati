package com.mrafp.idarati.Repository;

import com.mrafp.idarati.Model.Delai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;


public interface DelaiRepository extends JpaRepository<Delai,Long> {

    @NonNull
    Optional<Delai> findById(@NonNull Long id);
    @NonNull
    List<Delai> findAll();
    Delai findByDuree(Integer duree);
}
