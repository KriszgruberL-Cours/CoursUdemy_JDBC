package org.example.core.service;

import org.example.core.entity.Joueur;
import org.example.core.repository.JoueurRepositoryImpl;

public class JoueurService {

    private JoueurRepositoryImpl joueurRepository;

    public JoueurService() {
        this.joueurRepository = new JoueurRepositoryImpl();
    }

    public void createJoueur(Joueur joueur){
        joueurRepository.create(joueur);
    }

    public Joueur getJoueur(Long id){
        return joueurRepository.getById(id);
    }
}
