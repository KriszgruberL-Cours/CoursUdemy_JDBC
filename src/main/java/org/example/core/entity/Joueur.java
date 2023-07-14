package org.example.core.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Joueur {

    private Long id;
    private String nom;
    private String prenom;
    private Character sexe;

    public Joueur(String nom, String prenom, Character sexe) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
    }

    public Joueur() {
    }
}
