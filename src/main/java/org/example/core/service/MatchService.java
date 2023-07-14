package org.example.core.service;

import org.example.core.DAO.MatchDaoImpl;
import org.example.core.entity.Match;
import org.example.core.repository.MatchRepositoryImpl;
import org.example.core.repository.ScoreRepositoryImpl;

public class MatchService {

    private ScoreRepositoryImpl scoreRepository;
    private MatchRepositoryImpl matchRepository;
    private MatchDaoImpl matchDAO;

    public MatchService() {
        this.scoreRepository = new ScoreRepositoryImpl();
        this.matchRepository = new MatchRepositoryImpl();
//        this.matchDAO = new MatchDaoImpl();

    }

    public void enregistrerNouveauMatch(Match match){
        matchRepository.create(match);
        scoreRepository.create(match.getScore());
//        matchDAO.createMatchWithScore(match);
    }
}
