package com.sports.Sports.Service;

import com.sports.Sports.Repository.ScoreRepository;
import com.sports.Sports.model.ScoreBoard;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ScoreBoardServiceImp implements ScoreBoardService {
    private final ScoreRepository scoreRepository;

    public ScoreBoardServiceImp(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    @Override
    public List<ScoreBoard> getAllScoreBoard() {
        return scoreRepository.findAll();
    }

    @Override
    public Optional<ScoreBoard> getScoreBoardById(Long id) {
        return scoreRepository.findById(id);

    }

    @Override
    public ScoreBoard saveScoreBoard(ScoreBoard scoreBoard) {
        return scoreRepository.save(scoreBoard);
    }

    @Override
    public ScoreBoard updateScoreBoard(long id, ScoreBoard updatedScoreBoard) {
        return scoreRepository.findById(id)
                .map(existing -> {
                    existing.setSportsName(updatedScoreBoard.getSportsName());
                    existing.setTeamA(updatedScoreBoard.getTeamA());
                    existing.setTeamB(updatedScoreBoard.getTeamB());
                    existing.setScoreA(updatedScoreBoard.getScoreA());
                    existing.setScoreB(updatedScoreBoard.getScoreB());
                    existing.setStatus(updatedScoreBoard.getStatus());
                    existing.setWinner(updatedScoreBoard.getWinner());
                    existing.setLosser(updatedScoreBoard.getLosser());
                    return scoreRepository.save(existing);
                })
                .orElseGet(() -> {
                    updatedScoreBoard.setId(id);
                    return scoreRepository.save(updatedScoreBoard);
                });
    }

    @Override
    public void deleteScore(Long id) {
scoreRepository.deleteById(id);
    }
    @Override
    public List<ScoreBoard> getScoreBoardsBySport(String sportName) {
        return scoreRepository.findBySportsName(sportName);
    }

}
