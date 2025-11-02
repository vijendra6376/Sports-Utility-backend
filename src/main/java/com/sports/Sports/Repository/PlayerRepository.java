package com.sports.Sports.Repository;

import com.sports.Sports.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player ,Long> {
}
