package com.sports.Sports.Repository;

import com.sports.Sports.model.Role;
import com.sports.Sports.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role , Long> {
//Optional<User> findByEmail(String email);

    Optional<Role> findByName(String name);


}
