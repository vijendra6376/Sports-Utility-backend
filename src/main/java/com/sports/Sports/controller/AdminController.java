//package com.sports.Sports.controller;
//
//import com.sports.Sports.Service.UserService;
//import com.sports.Sports.Service.RoleService;
//import com.sports.Sports.model.User;
//import com.sports.Sports.model.Role;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/admin")
//public class AdminController {
//
//    private final UserService userService;
//    private final RoleService roleService;
//
//    @Autowired
//    public AdminController(UserService userService, RoleService roleService) {
//        this.userService = userService;
//        this.roleService = roleService;
//    }
//
//    // Example: list all users (ADMIN only)
//    @GetMapping("/users")
//    public ResponseEntity<List<User>> listAllUsers() {
//        return ResponseEntity.ok(userService.getAllUsers());
//    }
//
//    // Example: assign a role to a user (ADMIN only)
//    @PostMapping("/assign-role")
//    public ResponseEntity<?> assignRole(@RequestParam String email, @RequestParam String roleName) {
//        // roleName should be "ROLE_USER" or "ROLE_ADMIN"
//        User updated = userService.assignRoleToUser(email, roleName);
//        return ResponseEntity.ok("Assigned role " + roleName + " to user " + updated.getEmail());
//    }
//
//    // Example: a protected admin-only endpoint
//    @GetMapping("/dashboard")
//    public ResponseEntity<String> dashboard() {
//        return ResponseEntity.ok("Welcome to ADMIN dashboard");
//    }
//}
