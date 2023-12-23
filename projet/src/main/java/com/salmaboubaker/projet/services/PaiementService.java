package com.salmaboubaker.projet.services;

import com.salmaboubaker.projet.entities.Facture;
import com.salmaboubaker.projet.entities.Paiement;
import com.salmaboubaker.projet.repository.FactureRepository;
import com.salmaboubaker.projet.repository.PaiementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.salmaboubaker.projet.entities.Statut;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaiementService {

    private final PaiementRepository paiementRepository; // Assuming you have a repository for Paiement
    private final FactureRepository factureRepository; // Assuming you have a repository for Facture

    @Autowired
    public PaiementService(PaiementRepository paiementRepository, FactureRepository factureRepository) {
        this.paiementRepository = paiementRepository;
        this.factureRepository = factureRepository;
    }

    public Paiement createPaiement(Paiement paiement) {
        // Assuming the Paiement entity has a reference to the associated Facture
        Facture associatedFacture = paiement.getFacture();

        if (associatedFacture != null) {
            // Fetch the existing Facture entity by ID or any identifier
            Facture existingFacture = factureRepository.findById(associatedFacture.getId())
                    .orElse(null);

            if (existingFacture != null) {
                Paiement createdPaiement = paiementRepository.save(paiement);

                // Update the Facture status if the payment creation is successful
                if (createdPaiement != null) {
                    updateFactureStatus(existingFacture);
                }

                return createdPaiement;
            }
        }

        return null; // Handle cases where associated Facture is not found or null
    }


    private void updateFactureStatus(Facture facture) {
        if (facture != null) {
            facture.setStatut(Statut.Payee); // Assuming 'Payee' is the correct status
            factureRepository.saveAndFlush(facture); // Update only the status
        }
    }

    public List<Paiement> getAllPaiements() {
        return paiementRepository.findAll();
    }

    public Facture getFactureDetails(Long factureId) {
        return factureRepository.findById(factureId)
                .orElse(null); // Handle if Facture is not found for the given ID
    }

    public List<Paiement> createMultiplePayments(List<Paiement> paiements) {
        List<Paiement> createdPaiements = new ArrayList<>();

        for (Paiement paiement : paiements) {
            Facture associatedFacture = paiement.getFacture();

            if (associatedFacture != null) {
                Facture existingFacture = factureRepository.findById(associatedFacture.getId()).orElse(null);

                if (existingFacture != null) {
                    Paiement createdPaiement = paiementRepository.save(paiement);
                    if (createdPaiement != null) {
                        createdPaiements.add(createdPaiement);
                        updateFactureStatus(existingFacture);
                    }
                }
            }
        }

        return createdPaiements;
    }

}

