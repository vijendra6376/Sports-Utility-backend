package com.sports.Sports.Service;

import com.sports.Sports.model.Role;

import java.util.List;

public interface RoleService {
    Role saveRole(Role role);
    Role getRoleByName(String name);
    List<Role> getAllRoles();
}
