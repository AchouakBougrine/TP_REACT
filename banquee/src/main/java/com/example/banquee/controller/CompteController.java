package com.example.banquee.controller;

import com.example.banquee.entities.Compte;
import com.example.banquee.repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@CrossOrigin(origins = "http://localhost:8085")
@RestController
@RequestMapping("/banque") // Toutes les routes commencent par /banque
public class CompteController {

    @Autowired
    private CompteRepository compteRepository;

    // *** Récupérer tous les comptes ***
    @GetMapping(value = "/comptes", produces = {"application/json", "application/xml"})
    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }

    // *** Récupérer un compte par ID ***
    @GetMapping(value = "/comptes/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<Compte> getCompteById(@PathVariable Long id) {
        return compteRepository.findById(id)
                .map(compte -> ResponseEntity.ok().body(compte))
                .orElse(ResponseEntity.notFound().build());
    }

    // *** Ajouter un nouveau compte ***
    @PostMapping("/comptes")
    public ResponseEntity<Compte> createCompte(@RequestBody Compte compte) {
        Compte savedCompte = compteRepository.save(compte);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCompte);
    }
    // *** Mettre à jour un compte existant ***
    @PutMapping(value = "/comptes/{id}", consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<Compte> updateCompte(@PathVariable Long id, @RequestBody Compte compteDetails) {
        return compteRepository.findById(id)
                .map(compte -> {
                    compte.setSolde(compteDetails.getSolde());
                    compte.setDateCreation(compteDetails.getDateCreation());
                    compte.setType(compteDetails.getType());
                    Compte updatedCompte = compteRepository.save(compte);
                    return ResponseEntity.ok().body(updatedCompte);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // *** Supprimer un compte par ID ***
    @DeleteMapping(value = "/comptes/{id}", produces = "application/json")
    public ResponseEntity<Object> deleteCompte(@PathVariable Long id) {
        return compteRepository.findById(id)
                .map(compte -> {
                    compteRepository.delete(compte);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/banque/comptes")
    public ResponseEntity<Compte> addCompte(@RequestBody Compte compte) {
        System.out.println("Ajout du compte: " + compte);
        Compte savedCompte = compteRepository.save(compte);
        return ResponseEntity.ok(savedCompte);
    }

}
