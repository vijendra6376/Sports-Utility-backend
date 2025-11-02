package com.sports.Sports.Service;

import com.sports.Sports.model.ScoreBoard;

import java.util.List;
import java.util.Optional;

public interface ScoreBoardService {

    List<ScoreBoard> getAllScoreBoard();
    Optional<ScoreBoard> getScoreBoardById(Long id);
    ScoreBoard saveScoreBoard(ScoreBoard scoreBoard);
    ScoreBoard updateScoreBoard(long id , ScoreBoard updatedScoreBoard);
    void deleteScore(Long id);
    List<ScoreBoard> getScoreBoardsBySport(String sportName);

}
