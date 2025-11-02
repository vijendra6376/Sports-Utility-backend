package com.sports.Sports.Repository;

import com.sports.Sports.model.IssueRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRecordRepository extends JpaRepository<IssueRecord , Long> {
}
