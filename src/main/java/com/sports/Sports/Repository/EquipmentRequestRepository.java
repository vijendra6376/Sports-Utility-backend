package com.sports.Sports.Repository;

import com.sports.Sports.model.EquipmentRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipmentRequestRepository extends JpaRepository<EquipmentRequest , Long> {

    List<EquipmentRequest> findByUserEmail(String email);
}
