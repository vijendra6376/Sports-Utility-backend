package com.sports.Sports.Service;

import com.sports.Sports.Repository.TeamRepository;
import com.sports.Sports.model.Player;
import com.sports.Sports.model.Team;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TeamServiceImp implements TeamService {
    private final TeamRepository teamRepository;

    public TeamServiceImp(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Optional<Team> getTeamById(Long id) {
        return teamRepository.findById(id);
    }

    @Override
    public Team saveTeam(Team team) {
        // Set back-reference
        if (team.getPlayers() != null) {
            for (Player player : team.getPlayers()) {
                player.setTeam(team);  // Important!
            }
        }
        return teamRepository.save(team);
    }

    @Override
    public List<Team> getTeamsBySport(String sportName) {
        return teamRepository.findBySportNameIgnoreCase(sportName);
    }

    @Override
    public Team updateTeam(Long id, Team updatedTeam) {
        return teamRepository.findById(id)
                .map(existing -> {
                    existing.setTeamName(updatedTeam.getTeamName());
                    existing.setCaptain(updatedTeam.getCaptain());
                    existing.setViceCaptain(updatedTeam.getViceCaptain());
                    existing.setSportName(updatedTeam.getSportName());
                    existing.setCoachName(updatedTeam.getCoachName());
                    existing.setPlayersCount(updatedTeam.getPlayersCount());

                    // Set back-reference for players
                    if (updatedTeam.getPlayers() != null) {
                        for (Player player : updatedTeam.getPlayers()) {
                            player.setTeam(existing);
                        }
                        existing.getPlayers().clear(); // remove previous players
                        existing.getPlayers().addAll(updatedTeam.getPlayers()); // add updated players
                    }

                    return teamRepository.save(existing);
                })
                .orElseGet(() -> {
                    // Set back-reference for new team
                    if (updatedTeam.getPlayers() != null) {
                        for (Player player : updatedTeam.getPlayers()) {
                            player.setTeam(updatedTeam);
                        }
                    }
                    updatedTeam.setId(id);
                    return teamRepository.save(updatedTeam);
                });
    }
    @Override
    public List<String> getAllSportNames() {
        return teamRepository.findAllSportNames();
    }



    @Override
    public void deleteTeam(Long id) {
teamRepository.deleteById(id);
    }
}
