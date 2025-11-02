package com.sports.Sports.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class User {
@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
private String name ;
private String password;
@Column(unique = true)
private String email;
//private  boolean enabled = false;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
private Set<Role> roles = new HashSet<>();
}
