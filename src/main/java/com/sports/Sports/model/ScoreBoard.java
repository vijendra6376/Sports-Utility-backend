package com.sports.Sports.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ScoreBoard
{
@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String sportsName;
    private String teamA;
    private String teamB;

    private int scoreA;
    private int scoreB;
    private String status;
    private String winner;
    private String losser;

}
