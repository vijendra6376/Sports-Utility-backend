package com.sports.Sports.controller;

import com.sports.Sports.Service.TeamService;
import com.sports.Sports.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    /**
     * GET /teams
     * Accessible by USER and ADMIN: list all teams.
     */
    @GetMapping
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> list = teamService.getAllTeams();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/by-sport")
    public ResponseEntity<List<Team>> getTeamsBySport(@RequestParam String sport) {
        List<Team> teams = teamService.getTeamsBySport(sport);
        return ResponseEntity.ok(teams);
    }
    /**
     * GET /teams/{id}
     * Accessible by USER and ADMIN: view a single team by ID.
     */
    @GetMapping("/teams/{id:[0-9]+}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long id) {
        return teamService.getTeamById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    /**
     * POST /teams
     * Accessible only by ADMIN: create a new team.
     */
    @PostMapping
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        Team saved = teamService.saveTeam(team);
        return ResponseEntity.status(201).body(saved);
    }

    /**
     * PUT /teams/{id}
     * Accessible only by ADMIN: update existing team or create if not exists.
     */
    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateTeam(
            @PathVariable Long id,
            @RequestBody Team updatedTeam) {
        try {
            Team saved = teamService.updateTeam(id, updatedTeam);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.status(400)
                    .body("Failed to update Team: " + e.getMessage());
        }
    }

    /**
     * DELETE /teams/{id}
     * Accessible only by ADMIN: delete team by ID.
     */
    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteTeam(@PathVariable Long id) {
        Optional<Team> opt = teamService.getTeamById(id);
        if (opt.isPresent()) {
            teamService.deleteTeam(id);
            return ResponseEntity.ok("Deleted Team with id: " + id);
        } else {
            return ResponseEntity.status(404).body("Team not found with id: " + id);
        }
    }


    @GetMapping("/all-sports")
    public ResponseEntity<List<String>> getAllSportNames() {
        return ResponseEntity.ok(teamService.getAllSportNames());
    }


}
