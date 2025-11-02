package com.sports.Sports.controller;

import com.sports.Sports.Service.ScoreBoardService;
import com.sports.Sports.model.ScoreBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/scoreboards")
public class ScoreBoardController {

    private final ScoreBoardService scoreBoardService;

    @Autowired
    public ScoreBoardController(ScoreBoardService scoreBoardService) {
        this.scoreBoardService = scoreBoardService;
    }

    /**
     * GET /scoreboards
     * Accessible by USER and ADMIN: list all scoreboards.
     */
    @GetMapping
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<ScoreBoard>> getAllScoreBoards() {
        List<ScoreBoard> list = scoreBoardService.getAllScoreBoard();
        return ResponseEntity.ok(list);
    }

    /**
     * GET /scoreboards/{id}
     * Accessible by USER and ADMIN: view a single scoreboard by ID.
     */
    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> getScoreBoardById(@PathVariable Long id) {
        Optional<ScoreBoard> opt = scoreBoardService.getScoreBoardById(id);
        return opt
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404)
                        .body("ScoreBoard not found with id: " + id));
    }

    /**
     * POST /scoreboards
     * Accessible only by ADMIN: create a new scoreboard entry.
     */
    @PostMapping
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ScoreBoard> createScoreBoard(@RequestBody ScoreBoard scoreBoard) {
        ScoreBoard saved = scoreBoardService.saveScoreBoard(scoreBoard);
        return ResponseEntity.status(201).body(saved);
    }

    /**
     * PUT /scoreboards/{id}
     * Accessible only by ADMIN: update existing scoreboard or create if not exists.
     */
    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateScoreBoard(
            @PathVariable Long id,
            @RequestBody ScoreBoard updatedScoreBoard) {
        try {
            ScoreBoard saved = scoreBoardService.updateScoreBoard(id, updatedScoreBoard);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.status(400)
                    .body("Failed to update ScoreBoard: " + e.getMessage());
        }
    }

    /**
     * DELETE /scoreboards/{id}
     * Accessible only by ADMIN: delete scoreboard by ID.
     */
    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteScoreBoard(@PathVariable Long id) {
        Optional<ScoreBoard> opt = scoreBoardService.getScoreBoardById(id);
        if (opt.isPresent()) {
            scoreBoardService.deleteScore(id);
            return ResponseEntity.ok("Deleted ScoreBoard with id: " + id);
        } else {
            return ResponseEntity.status(404).body("ScoreBoard not found with id: " + id);
        }
    }
    @GetMapping("/bysport/{sportName}")
// @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<ScoreBoard>> getScoreBoardsBySport(@PathVariable String sportName) {
        List<ScoreBoard> list = scoreBoardService.getScoreBoardsBySport(sportName);
        return ResponseEntity.ok(list);
    }

}
