package com.sports.Sports.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class EquipmentRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String equipmentName;

    private String status; // PENDING, APPROVED, REJECTED

    private LocalDateTime requestDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
