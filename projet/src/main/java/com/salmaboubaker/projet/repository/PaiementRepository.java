package com.salmaboubaker.projet.repository;

import com.salmaboubaker.projet.entities.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {
    // You can add custom query methods if needed
}

