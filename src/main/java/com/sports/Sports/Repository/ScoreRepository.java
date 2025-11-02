package com.sports.Sports.Repository;

import com.sports.Sports.model.ScoreBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepository extends JpaRepository <ScoreBoard ,Long> {
    List<ScoreBoard> findBySportsName(String sportsName);

}
