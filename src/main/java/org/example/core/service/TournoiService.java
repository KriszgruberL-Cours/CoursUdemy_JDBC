package org.example.core.service;

import org.example.core.entity.Tournoi;
import org.example.core.repository.TournoiRepositoryImpl;

public class TournoiService {

    private TournoiRepositoryImpl tournoiRepository;

    public TournoiService() {
        this.tournoiRepository = new TournoiRepositoryImpl();
    }

    public Tournoi getTournoi(Long id){
        return tournoiRepository.getById(id);
    }

    public void createTournoi(Tournoi tournoi){
        tournoiRepository.create(tournoi);
    }
}
