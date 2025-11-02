package com.sports.Sports.Service;

import com.sports.Sports.model.Team;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    List<Team> getAllTeams();
    Optional<Team> getTeamById(Long id);
    Team saveTeam(Team team);
    Team updateTeam(Long id, Team updatedTeam);
    void deleteTeam(Long id);
    List<Team> getTeamsBySport(String sportName);
    List<String> getAllSportNames();

}
