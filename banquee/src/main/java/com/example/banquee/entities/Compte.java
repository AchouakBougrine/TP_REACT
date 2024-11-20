package com.example.banquee.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "compte")
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private double solde;

    // Utilisation de LocalDate pour la date de création
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateCreation;

    @Enumerated(EnumType.STRING)
    private TypeCompte type;

    public Compte(Object o, double v, LocalDate now, TypeCompte typeCompte) {
    }
}
