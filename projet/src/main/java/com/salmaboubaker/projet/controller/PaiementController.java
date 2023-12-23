package com.salmaboubaker.projet.controller;

import com.salmaboubaker.projet.entities.Facture;
import com.salmaboubaker.projet.entities.Paiement;
import com.salmaboubaker.projet.services.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/paiements")
public class PaiementController {

    private final PaiementService paiementService;

    @Autowired
    public PaiementController(PaiementService paiementService) {
        this.paiementService = paiementService;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/create")
    public ResponseEntity<Paiement> createPaiement(@RequestBody Paiement paiement) {
        Paiement createdPaiement = paiementService.createPaiement(paiement);
        return new ResponseEntity<>(createdPaiement, HttpStatus.CREATED);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/paiements")
    public ResponseEntity<List<Paiement>> getAllPaiements() {
        List<Paiement> paiements = paiementService.getAllPaiements();
        return new ResponseEntity<>(paiements, HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/factures/{factureId}")
    public ResponseEntity<Facture> getFactureDetails(@PathVariable Long factureId) {
        Facture factureDetails = paiementService.getFactureDetails(factureId);
        return new ResponseEntity<>(factureDetails, HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/create-multiple")
    public ResponseEntity<List<Paiement>> createMultiplePaiements(@RequestBody List<Paiement> paiements) {
        List<Paiement> createdPaiements = paiementService.createMultiplePayments(paiements);
        if (createdPaiements.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdPaiements, HttpStatus.CREATED);
    }

}

