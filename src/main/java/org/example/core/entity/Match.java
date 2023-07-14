package org.example.core.entity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Match {

    private Long id;
    private Joueur vainqueur;
    private Joueur finaliste;
    private Epreuve epreuve;

    private Score score;
}
