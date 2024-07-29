package com.mrafp.idarati.Repository;

import com.mrafp.idarati.Model.Cout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface CoutRepository extends JpaRepository<Cout, Long> {

    @NonNull
    Optional<Cout> findById(@NonNull Long id);
    @NonNull
    List<Cout> findAll();
    Cout findByValeur(Double valeur);
}
