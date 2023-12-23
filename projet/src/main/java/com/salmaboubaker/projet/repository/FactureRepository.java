package com.salmaboubaker.projet.repository;


import com.salmaboubaker.projet.entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FactureRepository extends JpaRepository<Facture, Long> {
    List<Facture> findByUserId(Long userId);
    Optional<Facture> findById(Long id); // Add this method to fetch a single facture by its ID

}

