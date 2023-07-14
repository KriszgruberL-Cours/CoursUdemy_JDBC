package org.example.core;

import org.example.core.entity.Epreuve;
import org.example.core.entity.Joueur;
import org.example.core.entity.Match;
import org.example.core.entity.Score;
import org.example.core.service.JoueurService;
import org.example.core.service.MatchService;
import org.example.core.service.TournoiService;

public class Cours {
    public static void main(String... args) {
        JoueurService joueurService = new JoueurService();
        TournoiService tournoiService = new TournoiService();
        MatchService matchService = new MatchService();
        Match match = new Match();
        Score score = new Score();
        score.setSet1((byte) 3);
        score.setSet2((byte) 4);
        score.setSet3((byte) 6);
        match.setScore(score);
        score.setMatch(match);
        Joueur federer = new Joueur();
        federer.setId(32L);
        Joueur murray = new Joueur();
        murray.setId(34L);

        match.setVainqueur(federer);
        match.setFinaliste(murray);

        Epreuve epreuve = new Epreuve();
        epreuve.setId(10L);
        match.setEpreuve(epreuve);

//        Joueur noah = new Joueur("Noah", "Yannick", 'H');
//        joueurService.createJoueur(noah);
//        System.out.println("L'id du joueur est :" + noah.getId());

        matchService.enregistrerNouveauMatch(match);
        System.out.println("L'id du match est :" + match.getId());



//        List<Tournoi> tournoiList = tournoiRepository.getList();
//        for (Tournoi tournoi : tournoiList)
//        System.out.printf("Id : %s Nom : %s , Code : %s %n", tournoi.getId(), tournoi.getNom(), tournoi.getCode() );


    }
}

