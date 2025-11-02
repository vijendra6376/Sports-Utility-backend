package com.sports.Sports.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity   // create table
@Data   // create getter and setter automatically
@Table(name = "Team")
public class Team {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCaptain() {
        return captain;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }

    public String getViceCaptain() {
        return viceCaptain;
    }

    public void setViceCaptain(String viceCaptain) {
        this.viceCaptain = viceCaptain;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public int getPlayersCount() {
        return playersCount;
    }

    public void setPlayersCount(int playersCount) {
        this.playersCount = playersCount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String teamName;
    private String captain;
    private String viceCaptain;
    private String sportName;
  @OneToMany(mappedBy = "team",cascade = CascadeType.ALL ,orphanRemoval = true)
    private List<Player> players;


    private String coachName;
    private int playersCount;

}
