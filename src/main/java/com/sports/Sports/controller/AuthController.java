//package com.sports.Sports.controller;
//
//import com.sports.Sports.Service.UserService;
//import com.sports.Sports.dto.RegisterRequest;
//import com.sports.Sports.dto.AuthResponse;
//import com.sports.Sports.model.User;
//import com.sports.Sports.model.Role;
//import com.sports.Sports.Repository.RoleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
//    private final UserService userService;
//    private final RoleRepository roleRepository;
//
//    @Autowired
//    public AuthController(UserService userService,
//                          RoleRepository roleRepository) {
//        this.userService = userService;
//        this.roleRepository = roleRepository;
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
//        // Check if email already exists
//        Optional<User> existing = userService.getUserByEmail(request.getEmail());
//        if (existing.isPresent()) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new AuthResponse("Email is already in use"));
//        }
//        // Create and save new user
//        User user = new User();
//        user.setName(request.getName());
//        user.setEmail(request.getEmail());
//        user.setPassword(request.getPassword());
//        User saved = userService.saveUser(user);
//
//        // Assign ROLE_USER by default
//        Role userRole = roleRepository.findByName("ROLE_USER")
//                .orElseThrow(() -> new RuntimeException("ROLE_USER not found"));
//        saved.getRoles().add(userRole);
//        userService.saveUser(saved); // update with role
//
//        return ResponseEntity.ok(new AuthResponse("User registered successfully"));
//    }
//}
