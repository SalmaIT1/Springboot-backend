package com.salmaboubaker.projet.services;
import com.salmaboubaker.projet.entities.Facture;
import com.salmaboubaker.projet.entities.Statut;
import com.salmaboubaker.projet.entities.User;
import com.salmaboubaker.projet.repository.FactureRepository;
import com.salmaboubaker.projet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FactureService {

    private final FactureRepository factureRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public FactureService(FactureRepository factureRepository) {
        this.factureRepository = factureRepository;
    }

    public List<Facture> getAllFactures() {
        return factureRepository.findAll();
    }

    public Optional<Facture> getFactureById(Long id) {
        return factureRepository.findById(id);
    }

    public Facture saveFacture(Facture facture) {
        return factureRepository.save(facture);
    }

    public void deleteFacture(Long id) {
        factureRepository.deleteById(id);
    }

    public List<Facture> getFacturesByUserId(Long userId) {
        // Log the user ID for debugging purposes
        System.out.println("Fetching factures for user ID: " + userId);

        // Implement logic to fetch factures by user ID from your data source (e.g., database)
        // For example, assuming you have a method in your repository to retrieve factures by user ID
        return factureRepository.findByUserId(userId);
    }

    // Somewhere in your service or controller

    public Facture createFacture(Facture facture, Long userId) {
        User user = userRepository.findById(userId).orElse(null); // Fetch the user object
        if (user != null) {
            facture.setUser(user); // Set the user object in the Facture entity
            return factureRepository.save(facture);
        } else {
            // Handle case when user is not found
            return null;
        }
    }


}
