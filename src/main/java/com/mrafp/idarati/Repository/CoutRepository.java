package com.mrafp.idarati.Repository;

import com.mrafp.idarati.Model.Cout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoutRepository extends JpaRepository<Cout, Long> {

    Cout findByValeur(Double valeur);
}
