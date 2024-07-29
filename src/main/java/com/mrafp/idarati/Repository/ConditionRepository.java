package com.mrafp.idarati.Repository;

import com.mrafp.idarati.Model.Antenne;
import com.mrafp.idarati.Model.Condition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface ConditionRepository extends JpaRepository<Condition, Long> {

    @NonNull
    Optional<Condition> findById(@NonNull Long id);
    @NonNull
    List<Condition> findAll();
    Optional<Condition> findByNomCondition(String nomCondition);
}
