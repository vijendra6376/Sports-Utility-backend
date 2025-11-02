package com.sports.Sports.Repository;

import com.sports.Sports.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findBySportNameIgnoreCase(String sportName);

    @Query("SELECT DISTINCT t.sportName FROM Team t")  // ✅ fixed field name
    List<String> findAllSportNames();
}
