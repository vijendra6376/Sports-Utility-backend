package com.sports.Sports.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data

public class IssueRecord {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    @JsonBackReference
    /*
    The current entity (e.g., Player) has a field (team) that is a reference to another entity (Team).

In the player table (in the database), there will be a column named team_id that stores the ID of the associated team
     */
    private Equipment equipment;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    private String issuedTo;
    private LocalDateTime issueDate;
    private Date returnDate;

    private boolean returned;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getIssuedTo() {
        return issuedTo;
    }

    public void setIssuedTo(String issuedTo) {
        this.issuedTo = issuedTo;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }
}

