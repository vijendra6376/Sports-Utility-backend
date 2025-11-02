package com.sports.Sports.Repository;

import com.sports.Sports.model.Events;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Events , Long> {
}
