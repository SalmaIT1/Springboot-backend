package com.salmaboubaker.projet.controller;


import com.salmaboubaker.projet.entities.Facture;
import com.salmaboubaker.projet.services.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/factures")
public class FactureController {

    private final FactureService factureService;

    @Autowired
    public FactureController(FactureService factureService) {
        this.factureService = factureService;
    }
@CrossOrigin(origins ="http://localhost:4200")
    @GetMapping
    public ResponseEntity<List<Facture>> getAllFactures() {
        List<Facture> factures = factureService.getAllFactures();
        return new ResponseEntity<>(factures, HttpStatus.OK);
    }
    @CrossOrigin(origins ="http://localhost:4200")
    @GetMapping("/{id}")
    public ResponseEntity<Facture> getFactureById(@PathVariable Long id) {
        return factureService.getFactureById(id)
                .map(facture -> new ResponseEntity<>(facture, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @CrossOrigin(origins ="http://localhost:4200")
    @PostMapping
    public ResponseEntity<?> createFacture(@RequestBody Facture facture, @RequestParam Long userId) {
        Facture createdFacture = factureService.createFacture(facture, userId);
        if (createdFacture != null) {
            return new ResponseEntity<>(createdFacture, HttpStatus.CREATED);
        } else {
            // Handle case when user is not found or something went wrong
            return new ResponseEntity<>("User not found or facture creation failed", HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins ="http://localhost:4200")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacture(@PathVariable Long id) {
        factureService.deleteFacture(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @CrossOrigin(origins ="http://localhost:4200")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Facture>> getFacturesByUserId(@PathVariable Long userId) {
        List<Facture> factures = factureService.getFacturesByUserId(userId);
        if (!factures.isEmpty()) {
            return ResponseEntity.ok(factures);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
