package com.mrafp.idarati.Repository;

import com.mrafp.idarati.Model.Administration;
import org.springframework.lang.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AdministrationRepository extends JpaRepository<Administration, Long> {

    @NonNull
    Optional<Administration> findById(@NonNull Long id);
    @NonNull
    List<Administration> findAll();
    Administration findByNomAdmin(@NonNull String nomAdmin);

}
