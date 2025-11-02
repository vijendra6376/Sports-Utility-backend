package com.sports.Sports.Repository;

import com.sports.Sports.model.Rules;
import org.apache.tomcat.util.digester.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleRepository extends JpaRepository<Rules,Long> {
}
