package org.example.core.entity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Epreuve {

    private Long id;
    private Short annee;
    private Tournoi tournoi;
    private Character typeEpreuve;
}
